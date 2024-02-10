package com.example.tinkofftask.features.mainscreen.presentation.ui

import android.os.Bundle
import android.util.Log
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
import com.example.tinkofftask.databinding.FragmentMainScreenBinding
import com.example.tinkofftask.features.di.AdapterModule
import com.example.tinkofftask.features.mainscreen.presentation.event.ReloadListOfFilms
import com.example.tinkofftask.features.mainscreen.presentation.model.FilmUi
import com.example.tinkofftask.features.mainscreen.presentation.ui.recyclerview.FilmAdapter
import com.example.tinkofftask.features.mainscreen.presentation.vm.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val viewModel: MainScreenViewModel by viewModels()

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var customAdapterFactory: AdapterModule
    private val filmDetailsListener: (
        filmUi: FilmUi
    ) -> Unit = {
            filmUi ->
        val action = MainScreenFragmentDirections
            .actionMainScreenFragmentToDetailFilmFragment(filmUi)
        findNavController().navigate(action)
    }
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
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
        filmAdapter = customAdapterFactory.createFilmAdapter(filmDetailsListener)
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = filmAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observerButton() {
        binding.error.errorButtonReload.setOnClickListener {
            viewModel.handleIntent(ReloadListOfFilms())
        }
    }

    private fun observerFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFlow.collect { result ->
                    Log.d("LogCat", "$result")
                    binding.spinner.root.isVisible = result.isLoading
                    filmAdapter.submitList(result.listOfFilms)
                    binding.error.root.isVisible = result.error != null
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}