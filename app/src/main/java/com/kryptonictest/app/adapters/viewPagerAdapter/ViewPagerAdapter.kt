package com.kryptonictest.app.adapters.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val mFragmentTitleList: MutableList<String> = ArrayList()
    fun addFrag(fragment: Fragment, title: String) {
        if (!fragment.isAdded) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }
    }

    fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }
}