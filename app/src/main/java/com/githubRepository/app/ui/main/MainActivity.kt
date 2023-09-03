package com.githubRepository.app.ui.main

import android.os.Bundle
import com.githubRepository.R
import com.githubRepository.app.adapters.viewPagerAdapter.ViewPagerAdapter
import com.githubRepository.app.bases.BaseActivity
import com.githubRepository.app.ui.main.favorite.FavoritesFragment
import com.githubRepository.app.ui.main.home.HomeFragment
import com.githubRepository.databinding.ActivityMainBinding
import com.githubRepository.utils.extensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Home"
        setupViewPager()
        iniItems()
    }

    private fun iniItems() {
        binding.bnv.setOnItemSelectedListener { item ->
            moveTab(item.itemId)
            true
        }
    }

    private fun moveTab(itemId: Int) {
        when (itemId) {
            R.id.item_home -> {
                title = "Home"
                binding.viewPager.setCurrentItem(0, true)
            }

            R.id.item_favorites -> {
                title = "Favorites"
                binding.viewPager.setCurrentItem(1, true)
            }
        }
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFrag(HomeFragment(), "")
        viewPagerAdapter.addFrag(FavoritesFragment(), "")
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.isUserInputEnabled = false
    }
}