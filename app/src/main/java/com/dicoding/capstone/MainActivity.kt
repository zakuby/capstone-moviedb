package com.dicoding.capstone

import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.core.base.BaseActivity
import com.dicoding.movie.ui.MovieFragment
import com.dicoding.tvshow.ui.TvShowFragment
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseMainActivity : BaseActivity<ActivityMainBinding>()

@AndroidEntryPoint
class MainActivity : BaseMainActivity() {

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    private val pagerAdapter by lazy { MainPagerAdapter(supportFragmentManager, lifecycle) }

    override fun initBinding() {
        binding.apply {
            bottomNavBar.apply {
                itemIconTintList = null
                setOnNavigationItemSelectedListener { item ->
                    viewpager.setCurrentItem(
                        when (item.itemId) {
                            R.id.movies_fragment -> 0
                            R.id.tv_show_fragment -> 1
                            else -> 2
                        }, false)
                    true
                }
            }
            viewpager.apply {
                offscreenPageLimit = 3
                isUserInputEnabled = false
                adapter = pagerAdapter
            }
        }
    }

    private inner class MainPagerAdapter(
        fm: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount(): Int = 3
        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> loadFavoriteFragment()
        }
    }

    private fun loadFavoriteFragment(): Fragment {
        return try {
            Class.forName("com.dicoding.favorite.ui.FavoriteFragment").newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            Fragment()
        }
    }
}
