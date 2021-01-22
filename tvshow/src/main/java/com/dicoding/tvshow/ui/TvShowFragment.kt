package com.dicoding.tvshow.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.base.BaseFragment
import com.dicoding.core.data.local.models.FilterType
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.utils.isGone
import com.dicoding.core.utils.isShimmerStart
import com.dicoding.core.utils.observe
import com.dicoding.tvshow.R
import com.dicoding.tvshow.databinding.FragmentTvShowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : BaseFragment<FragmentTvShowBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentTvShowBinding
        get() = FragmentTvShowBinding::inflate

    private val viewModel: TvShowViewModel by viewModels()

    private val adapterTvShow by lazy { TvShowAdapter { println(it) } }

    override fun initBinding() {
        binding.apply {
            errorLayout.retryButton.setOnClickListener { retryLoadTvShow() }
            recyclerView.apply {
                adapter = adapterTvShow
                layoutManager = LinearLayoutManager(activity)
            }
            searchView.apply {
                setQuery(query, false)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        viewModel.searchTvShows(query, FilterType.BY_KEYWORDS)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = false
                })
                view?.findViewById<ImageView>(R.id.search_close_btn)?.setOnClickListener {
                    retryLoadTvShow()
                    setQuery("", false)
                    isIconified = true
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        observe(viewModel.tvShows, adapterTvShow::submitList)
        observe(viewModel.resultPaging, { result ->
            when (result) {
                is ResultPaging.Empty -> {
                    binding.apply {
                        errorLayout.errorView.isGone(!result.isEmpty)
                        container.isGone(result.isEmpty)
                    }
                }
                is ResultPaging.Error -> binding.errorLayout.message.text = result.error.message
                is ResultPaging.Loading -> {
                    binding.apply {
                        shimmerView.isShimmerStart(result.isLoading)
                        shimmerView.isGone(!result.isLoading)
                        layoutContainer.isGone(result.isLoading)
                    }
                }
            }
        })
    }

    private fun retryLoadTvShow() = viewModel.searchTvShows("")
}
