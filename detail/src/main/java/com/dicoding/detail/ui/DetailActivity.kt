package com.dicoding.detail.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.base.BaseActivity
import com.dicoding.core.data.local.models.Cast
import com.dicoding.core.data.local.models.Review
import com.dicoding.core.data.local.models.Video
import com.dicoding.core.data.remote.response.Result
import com.dicoding.core.data.remote.response.ResultPaging
import com.dicoding.core.di.FavoriteModuleDependencies
import com.dicoding.core.ui.CustomDialog
import com.dicoding.core.ui.WebViewActivity
import com.dicoding.core.utils.formatDate
import com.dicoding.core.utils.loadImageUrl
import com.dicoding.core.utils.observe
import com.dicoding.core.utils.setProgressRating
import com.dicoding.detail.R
import com.dicoding.detail.adapter.CastListAdapter
import com.dicoding.detail.adapter.GenreListAdapter
import com.dicoding.detail.adapter.ReviewListAdapter
import com.dicoding.detail.adapter.VideoListAdapter
import com.dicoding.detail.data.local.Detail
import com.dicoding.detail.data.local.DetailType
import com.dicoding.detail.databinding.ActivityDetailBinding
import com.dicoding.detail.di.DaggerDetailComponent
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>(false) {

    override val bindingInflater: (LayoutInflater) -> ActivityDetailBinding
        get() = ActivityDetailBinding::inflate

    companion object {
        const val EXTRA_DETAIL_ID = "detailId"
        const val EXTRA_DETAIL_TYPE = "detailType"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var customDialog: CustomDialog

    private val viewModel: DetailViewModel by viewModels { viewModelFactory }

    private val castAdapter by lazy { CastListAdapter() }

    private val genreAdapter by lazy { GenreListAdapter() }

    private val videoAdapter by lazy { VideoListAdapter(this::openYoutube) }

    private val reviewAdapter by lazy { ReviewListAdapter(this::viewFullReview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerDetailComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        subscribeUI()
    }

    override fun initBinding() {
        binding.apply {
            backButton.setOnClickListener { finish() }
            recyclerViewCast.apply {
                layoutManager = LinearLayoutManager(
                    this@DetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = castAdapter
            }
            recyclerViewVideo.apply {
                layoutManager = LinearLayoutManager(
                    this@DetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = videoAdapter
            }
            recyclerViewGenre.apply {
                layoutManager = LinearLayoutManager(
                    this@DetailActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = genreAdapter
            }
            recyclerViewReview.apply {
                layoutManager = LinearLayoutManager(this@DetailActivity)
                adapter = reviewAdapter
            }
        }
    }

    private fun subscribeUI() {
        val id = intent.getIntExtra(EXTRA_DETAIL_ID, -1)
        val type = intent.getSerializableExtra(EXTRA_DETAIL_TYPE) as DetailType
        viewModel.setDetail(id, type)
        observe(viewModel.casts, this::renderCasts)
        observe(viewModel.detail, this::renderDetail)
        observe(viewModel.videos, this::renderMovieVideos)
        observe(viewModel.reviews, reviewAdapter::submitList)
        observe(viewModel.isFavored, { isFavored ->
            binding.favoriteButtonFab.setImageResource(
                if (isFavored) R.drawable.ic_favorite_remove_fab
                else R.drawable.ic_favorite_fab
            )
        })
        observe(viewModel.resultReviews, { resultPaging ->
            when (resultPaging) {
                is ResultPaging.Loading -> {
                    val isLoading = resultPaging.isLoading
                    binding.shimmerViewReview.apply {
                        isGone = !isLoading
                        if (isLoading) startShimmer() else stopShimmer()
                    }
                    binding.reviewView.isGone = isLoading
                }
                is ResultPaging.Empty -> binding.containerReview.isGone = resultPaging.isEmpty
                is ResultPaging.Error -> binding.containerReview.isGone = true
            }
        })
        observe(viewModel.onFavoredEvent, { isFavored ->
            binding.favoriteButtonFab.setImageResource(
                if (isFavored) R.drawable.ic_favorite_remove_fab
                else R.drawable.ic_favorite_fab
            )
            if (isFavored) customDialog.showRemoveFromFavoriteDialog(this)
            else customDialog.showAddToFavoriteDialog(this)
        })
    }

    private fun renderCasts(result: Result<List<Cast>>) {
        when (result) {
            is Result.Success -> castAdapter.submitList(result.data)
            is Result.Error -> binding.containerCast.isGone = true
            is Result.Loading -> binding.apply {
                shimmerViewCast.apply {
                    isGone = !result.isLoading
                    if (result.isLoading) startShimmer() else stopShimmer()
                }
                castView.isGone = result.isLoading
            }
        }
    }

    private fun renderDetail(result: Result<Detail>) {
        when (result) {
            is Result.Success -> bindDetail(result.data)
            is Result.Loading -> binding.apply {
                shimmerViewDetail.apply {
                    isGone = !result.isLoading
                    if (result.isLoading) startShimmer() else stopShimmer()
                }
                parentDetail.isGone = result.isLoading
            }
        }
    }

    private fun bindDetail(detail: Detail) {
        binding.apply {
            toolbarImage.loadImageUrl(detail.backgroundImage)
            posterImage.loadImageUrl(detail.posterImage)
            progressBarRating.setProgressRating(detail.rate)
            progressRatingScore.text = detail.rate
            detailTitle.text = detail.title
            detailDate.formatDate(detail.date)
            detailOverviewDesc.text = detail.description
            genreAdapter.submitList(detail.genres ?: emptyList())
            favoriteButtonFab.setOnClickListener { viewModel.favorDetail(detail) }
        }
    }

    private fun renderMovieVideos(result: Result<List<Video>>) {
        when (result) {
            is Result.Success -> videoAdapter.submitList(result.data)
            is Result.Error -> binding.containerVideos.isGone = true
            is Result.Loading -> binding.apply {
                shimmerViewVideos.apply {
                    isGone = !result.isLoading
                    if (result.isLoading) startShimmer() else stopShimmer()
                }
                videoView.isGone = result.isLoading
            }
        }
    }

    private fun viewFullReview(review: Review) {
        val i = Intent(this, WebViewActivity::class.java).apply {
            putExtra("url", review.url)
            putExtra("title", "Review Detail by ${review.author}")
        }
        startActivity(i)
    }

    private fun openYoutube(key: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("http://www.youtube.com/watch?v=$key")
        )
        try {
            startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }
}