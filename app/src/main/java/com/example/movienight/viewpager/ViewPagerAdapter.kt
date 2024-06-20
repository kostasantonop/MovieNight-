package com.example.movienight.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movienight.viewpager.tab1recyclerview.Tab1RecyclerViewFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tab1RecyclerViewFragment()
            1 -> Tab2RecyclerViewFragment()
            2 -> Tab3Fragment()
            else -> Tab1RecyclerViewFragment()
        }
    }
}
