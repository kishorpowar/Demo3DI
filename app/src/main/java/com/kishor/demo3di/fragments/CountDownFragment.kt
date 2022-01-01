package com.kishor.demo3di.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kishor.demo3di.R
import java.util.*


class CountDownFragment : Fragment() {

    companion object {
        fun newInstance() = CountDownFragment()
    }

    private var mTextViewCountDown: TextView? = null
    private var mButtonStartPause: Button? = null
    private lateinit var viewModel: CountDownViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.count_down_fragment, container, false)
        mButtonStartPause = root.findViewById(R.id.button_start_reset) as Button
        mTextViewCountDown = root.findViewById(R.id.text_view_countdown) as TextView

        mButtonStartPause!!.setOnClickListener {
            viewModel.onButtonClick()
        }
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountDownViewModel::class.java)

        viewModel.mTxtTimer.observe(viewLifecycleOwner,{
            mTextViewCountDown!!.text = it
        })

        viewModel.mButtonText.observe(viewLifecycleOwner,{
            mButtonStartPause!!.text = it
        })

    }



    override fun onStop() {
        super.onStop()

        viewModel.saveTimer( requireContext())

    }

    override fun onStart() {
        super.onStart()
      viewModel.getTimer(requireContext())


    }

}