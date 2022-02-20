package com.rahul.viewpagernetworkcall.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rahul.viewpagernetworkcall.fragments.FirstFragment
import com.rahul.viewpagernetworkcall.fragments.SecondFragment
import com.rahul.viewpagernetworkcall.fragments.ThirdFragment


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle:Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle) {


    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
       return when (position) {
            0 ->  FirstFragment()
            1 ->  SecondFragment()
            2->  ThirdFragment()
           else ->Fragment()
        }

    }

}