package com.hul.sportzin.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.*
class TeamPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(
        fragmentManager, lifecycle
    ) {

    private val mFragmentList: MutableList<Fragment> = ArrayList()
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = mFragmentList[position]

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

}