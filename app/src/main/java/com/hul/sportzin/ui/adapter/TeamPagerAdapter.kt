package com.hul.sportzin.ui.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hul.sportzin.ui.fragment.team.TeamFragment

class TeamPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    private val mFragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()


    override fun getCount() = 2

    override fun getItem(position: Int) = mFragmentList[position]

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        fragmentTitleList.add(title)

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
}