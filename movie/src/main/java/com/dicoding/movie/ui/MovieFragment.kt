package com.dicoding.movie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.core.base.BaseFragment
import com.dicoding.core.utils.observe
import com.dicoding.movie.data.local.FilterType
import com.dicoding.movie.R
import com.dicoding.movie.databinding.FragmentMovieBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.dlg_bottom_filter.*

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentMovieBinding
        get() = FragmentMovieBinding::inflate

    private val viewModel: MovieViewModel by viewModels()

    private val adapterMovie by lazy { MovieAdapter{ movie -> println(movie)} }
    private val adapterGenre by lazy { GenreAdapter() }

    override fun initBinding() {
        binding.apply {
            errorLayout.retryButton.setOnClickListener { retryLoadMovie() }
            recyclerView.apply {
                adapter = adapterMovie
                layoutManager = LinearLayoutManager(activity)
            }
            searchView.apply {
                setQuery(query, false)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.searchMovies(query, FilterType.BY_KEYWORDS)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = false
                })
                view?.findViewById<ImageView>(R.id.search_close_btn)?.setOnClickListener {
                    viewModel.searchMovies("")
                    setQuery("", false)
                    isIconified = true
                }
            }
            btnFilter.setOnClickListener { showBottomFilter() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        observe(viewModel.movies, adapterMovie::submitList)
        observe(viewModel.genres, adapterGenre::submitList)
    }

    private fun retryLoadMovie() = viewModel.searchMovies("")

    private fun showBottomFilter() {
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.dlg_bottom_filter, bottom_sheet_container).apply {
            findViewById<RecyclerView>(R.id.recycler_view_genre).apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = adapterGenre
            }
            findViewById<MaterialButton>(R.id.btn_add_apply).setOnClickListener {
                bottomSheetDialog.dismiss()
                binding.searchView.apply {
                    setQuery("", false)
                    isIconified = true
                }
                viewModel.searchMovies(adapterGenre.getCheckedGenres(), filterType = FilterType.BY_GENRES)
            }
        }
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.show()
    }
}