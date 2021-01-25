package com.dicoding.tvshow.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.base.BaseFragment
import com.dicoding.core.domain.model.FilterType
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.utils.isGone
import com.dicoding.core.utils.isShimmerStart
import com.dicoding.core.utils.observe
import com.dicoding.detail.data.local.DetailType
import com.dicoding.detail.ui.DetailActivity
import com.dicoding.tvshow.R
import com.dicoding.tvshow.databinding.FragmentTvShowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : BaseFragment<FragmentTvShowBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentTvShowBinding
        get() = FragmentTvShowBinding::inflate

    private val viewModel: TvShowViewModel by viewModels()

    private val adapterTvShow by lazy { TvShowAdapter { gotoDetailTvShow(it.id) } }

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
                        errorLayout.message.text = "Data is empty"
                        container.isGone(result.isEmpty)
                    }
                }
                is ResultPaging.Error -> binding.apply {
                    container.isGone(true)
                    errorLayout.errorView.isGone(false)
                    errorLayout.message.text = result.error.message
                }
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

    private fun gotoDetailTvShow(id: Int){
        val detailIntent = Intent(activity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_DETAIL_ID, id)
            putExtra(DetailActivity.EXTRA_DETAIL_TYPE, DetailType.TV_SHOW)
        }
        startActivity(detailIntent)
    }
}
