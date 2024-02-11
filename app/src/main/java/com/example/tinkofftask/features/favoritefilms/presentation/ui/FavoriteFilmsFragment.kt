package com.example.tinkofftask.features.favoritefilms.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tinkofftask.databinding.FragmentFavoriteFilmsBinding
import com.example.tinkofftask.features.favoritefilms.presentation.ui.recyclerview.FavoriteFilmAdapter
import com.example.tinkofftask.features.favoritefilms.presentation.vm.FavoriteFilmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFilmsFragment : Fragment() {

    private val viewModel: FavoriteFilmsViewModel by viewModels()

    private var _binding: FragmentFavoriteFilmsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var filmAdapter: FavoriteFilmAdapter

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteFilmsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observerFlow()
        observerButton()
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observerButton() {
        binding.buttonPopular.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { result ->
                    binding.spinner.root.isVisible = result.isLoading
                    filmAdapter.submitList(result.listOfFilms)

                    if (result.isLoading) {
                        binding.buttonPopular.isVisible = false
                        binding.buttonFavorite.isVisible = false
                    } else {
                        binding.buttonPopular.isVisible = true
                        binding.buttonFavorite.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}