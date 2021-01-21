package com.dicoding.capstone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.movie.ui.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val pagerAdapter by lazy { MainPagerAdapter(supportFragmentManager, lifecycle) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBinding()
    }

    private fun initBinding(){
        binding.apply {
            bottomNavBar.apply {
                itemIconTintList = null
                setOnNavigationItemSelectedListener { item ->
                    viewpager.setCurrentItem(
                        when (item.itemId){
                            R.id.movies_fragment -> 0
                            R.id.favorite_fragment -> 1
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
            else -> Fragment()
        }
    }
}