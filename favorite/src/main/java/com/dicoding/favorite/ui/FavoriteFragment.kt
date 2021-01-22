package com.dicoding.favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.capstone.FavoriteModuleDependencies
import com.dicoding.core.base.BaseFragment
import com.dicoding.core.data.local.models.TvShow
import com.dicoding.core.utils.observe
import com.dicoding.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.favorite.di.DaggerFavoriteComponent
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FavoriteViewModel by viewModels { viewModelFactory }

    override val bindingInflater: (LayoutInflater) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    private val movieAdapter by lazy {
        FavoriteMovieAdapter { println("TODO: On click") }
    }

    private val tvShowAdapter by lazy {
        FavoriteTvShowAdapter { println("TODO: On click") }
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        observe(viewModel.movies, movieAdapter::submitList)
        observe(viewModel.tvShows, tvShowAdapter::submitList)
    }

    override fun initBinding() {
        binding.apply {
            recyclerViewMovie.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = movieAdapter
            }
            recyclerViewTvShow.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tvShowAdapter
            }
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0 -> onTabMovieSelected()
                        1 -> onTabTvShowSelected()
                    }
                }
            })
        }
    }

    private fun onTabMovieSelected(){
        binding.apply {
            emptyLayout.visibility = if (movieAdapter.isEmpty()) View.VISIBLE else View.GONE
            recyclerViewMovie.visibility = if (movieAdapter.isEmpty()) View.GONE else View.VISIBLE
            recyclerViewTvShow.visibility = View.GONE
        }
    }

    private fun onTabTvShowSelected(){
        tvShowAdapter.submitList(listOf(TvShow(0, "a", "01-01-1997", "b", "c", "", "")))
        binding.apply {
            emptyLayout.visibility = if (tvShowAdapter.isEmpty()) View.VISIBLE else View.GONE
            recyclerViewTvShow.visibility = if (tvShowAdapter.isEmpty()) View.GONE else View.VISIBLE
            recyclerViewMovie.visibility = View.GONE
        }
    }
}
