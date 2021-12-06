package com.example.truecreditslist.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaper(list:List<Fragment>,
                      fm:FragmentManager,
                      lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

    private val frgamentList = list

    override fun getItemCount(): Int {
       return frgamentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return frgamentList[position]
    }

}