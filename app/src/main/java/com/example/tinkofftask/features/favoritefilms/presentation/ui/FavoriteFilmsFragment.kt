package com.example.tinkofftask.features.favoritefilms.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.tinkofftask.R
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

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        val searchView = menuItem.actionView as? SearchView
                        searchView?.queryHint = getText(R.string.search_descrption)
                        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                            override fun onQueryTextSubmit(p0: String?): Boolean {
                                searchView.clearFocus()
                                return true
                            }
                            override fun onQueryTextChange(newText: String?): Boolean {
                                if (newText != null) {
                                    runQuery(newText)
                                }
                                return true
                            }
                        })
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun runQuery(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchFavoriteFilmByName(searchQuery)
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