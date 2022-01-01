package com.kishor.demo3di.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kishor.demo3di.fragments.CountDownFragment
import com.kishor.demo3di.fragments.ListFragment

const val LISTFFRAGMENT_INDEX = 0
const val COUNTDOWN_INDEX = 1
class FragmentSlidePagerAdapter(fa: FragmentActivity) :   FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            ListFragment()
        } else {
            CountDownFragment()
        }
    }

}