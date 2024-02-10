package com.example.tinkofftask.features.filmdetail.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.tinkofftask.R
import com.example.tinkofftask.databinding.FragmentDetailFilmBinding
import com.example.tinkofftask.features.filmdetail.presentation.event.GetFilmDetailEvent
import com.example.tinkofftask.features.filmdetail.presentation.event.ReloadFilmDetailEvent
import com.example.tinkofftask.features.filmdetail.presentation.model.FilmDetailUi
import com.example.tinkofftask.features.filmdetail.presentation.vm.FilmDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFilmFragment : Fragment() {

    private val viewModel: FilmDetailViewModel by viewModels()

    private var _binding: FragmentDetailFilmBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFilmFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFilmBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.handleIntent(GetFilmDetailEvent(filmId = args.film.filmId))

        observerFlow()
        observerButton()
    }

    private fun observerButton() {
        binding.leftArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.error.errorButtonReload.setOnClickListener {
            viewModel.handleIntent(ReloadFilmDetailEvent(filmId = args.film.filmId))
        }
    }

    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { result ->
                    Log.d("LogCat", "$result")
                    binding.spinner.root.isVisible = result.isLoading
                    if (result.filmDetail != null) {
                        setDetailFilmFilds(result.filmDetail)
                    }
                    binding.error.root.isVisible = result.error != null
                }
            }
        }
    }

    private fun setDetailFilmFilds(filmDetail: FilmDetailUi) {
        loadImage(binding.filmPoster, filmDetail.posterUrl)
        binding.filmName.text = filmDetail.nameRu
        val genres: String = filmDetail.genres.joinToString { it -> it }
        val filmGenres = "${getString(R.string.genres)}: $genres"
        binding.filmGenres.text = filmGenres
        binding.filmDescription.text = filmDetail.description
        val countries: String = filmDetail.countries.joinToString { it -> it }
        val filmCountries = "${getString(R.string.contries)}: $countries"
        binding.filmCountries.text = filmCountries
    }

    private fun loadImage(imageView: AppCompatImageView, imageUri: String) {
        Glide.with(imageView.context)
            .load(imageUri)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .into(imageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}