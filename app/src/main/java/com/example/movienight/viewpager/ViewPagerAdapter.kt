package com.example.movienight.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movienight.viewpager.tab1recyclerview.Tab1RecyclerViewFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1RecyclerViewFragment()
            1 -> Tab3Fragment()
            2 -> Tab2Fragment()
            else -> Tab1RecyclerViewFragment()
        }
    }
}