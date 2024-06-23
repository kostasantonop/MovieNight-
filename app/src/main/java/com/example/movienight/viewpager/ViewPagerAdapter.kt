package com.example.movienight.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movienight.viewpager.fragment.FavoritesFragment
import com.example.movienight.viewpager.fragment.PopularFragment
import com.example.movienight.viewpager.fragment.SearchFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PopularFragment()
            1 -> SearchFragment()
            2 -> FavoritesFragment()
            else -> PopularFragment()
        }
    }
}
