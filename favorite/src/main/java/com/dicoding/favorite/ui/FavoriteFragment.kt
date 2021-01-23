package com.dicoding.favorite.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.di.FavoriteModuleDependencies
import com.dicoding.core.base.BaseFragment
import com.dicoding.core.ui.CustomDialog
import com.dicoding.core.utils.observe
import com.dicoding.detail.data.local.DetailType
import com.dicoding.detail.ui.DetailActivity
import com.dicoding.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.favorite.di.DaggerFavoriteComponent
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var customDialog: CustomDialog

    private val viewModel: FavoriteViewModel by viewModels { viewModelFactory }

    override val bindingInflater: (LayoutInflater) -> FragmentFavoriteBinding
        get() = FragmentFavoriteBinding::inflate

    private val movieAdapter by lazy {
        FavoriteMovieAdapter(
            { goToDetail(it.id, DetailType.MOVIE) },
            { viewModel.removeFavorite(it, DetailType.MOVIE) }
        )
    }

    private val tvShowAdapter by lazy {
        FavoriteTvShowAdapter(
            { goToDetail(it.id, DetailType.TV_SHOW) },
            { viewModel.removeFavorite(it, DetailType.TV_SHOW) }
        )
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
        observe(viewModel.movies, {
            movieAdapter.submitList(it)
            if (binding.tabLayout.selectedTabPosition == 0) onTabMovieSelected()
        })
        observe(viewModel.tvShows, {
            tvShowAdapter.submitList(it)
            if (binding.tabLayout.selectedTabPosition == 1) onTabTvShowSelected()
        })
        observe(viewModel.removeMovieEvent, {
            customDialog.showRemoveFromFavoriteDialog(requireContext())
        })
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
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> onTabMovieSelected()
                        1 -> onTabTvShowSelected()
                    }
                }
            })
        }
    }

    private fun onTabMovieSelected() {
        binding.apply {
            emptyLayout.visibility = if (movieAdapter.isEmpty()) View.VISIBLE else View.GONE
            recyclerViewMovie.visibility = if (movieAdapter.isEmpty()) View.GONE else View.VISIBLE
            recyclerViewTvShow.visibility = View.GONE
        }
    }

    private fun onTabTvShowSelected() {
        binding.apply {
            emptyLayout.visibility = if (tvShowAdapter.isEmpty()) View.VISIBLE else View.GONE
            recyclerViewTvShow.visibility = if (tvShowAdapter.isEmpty()) View.GONE else View.VISIBLE
            recyclerViewMovie.visibility = View.GONE
        }
    }

    private fun goToDetail(id: Int, type: DetailType) {
        val detailIntent = Intent(activity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DETAIL_ID, id)
            putExtra(DetailActivity.EXTRA_DETAIL_TYPE, type)
        }
        startActivity(detailIntent)
    }
}
