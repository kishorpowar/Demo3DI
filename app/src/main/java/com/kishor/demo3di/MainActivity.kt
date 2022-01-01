package com.kishor.demo3di

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kishor.demo3di.adapters.COUNTDOWN_INDEX
import com.kishor.demo3di.adapters.FragmentSlidePagerAdapter
import com.kishor.demo3di.adapters.LISTFFRAGMENT_INDEX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagerAdapter = FragmentSlidePagerAdapter(this)
        pager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }


    private fun getTabTitle(position: Int): CharSequence? {
        return when (position) {
            LISTFFRAGMENT_INDEX -> getString(R.string.list_fragment)
            COUNTDOWN_INDEX -> getString(R.string.count_down_fragment)
            else -> null
        }
    }
}