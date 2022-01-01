package com.kishor.demo3di.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kishor.demo3di.R


class CountDownFragment : Fragment() {

    companion object {
        fun newInstance() = CountDownFragment()
    }

    private var mTextViewCountDown: TextView? = null
    private var mTextViewCountUp: TextView? = null
    private var mButtonStartPause: Button? = null
    private var mUpTimerStartPause: Button? = null
    private lateinit var viewModel: CountDownViewModel
    private lateinit var countUpViewModel: CountUpViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.count_down_fragment, container, false)
        mButtonStartPause = root.findViewById(R.id.button_start_reset) as Button
        mTextViewCountDown = root.findViewById(R.id.text_view_countdown) as TextView
        mTextViewCountUp = root.findViewById(R.id.txtcountup) as TextView
        mUpTimerStartPause = root.findViewById(R.id.btn_start) as Button

        mButtonStartPause!!.setOnClickListener {
            viewModel.onButtonClick()
        }


        mUpTimerStartPause!!.setOnClickListener {
            countUpViewModel.onStartClick(requireContext())
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountDownViewModel::class.java)
        countUpViewModel = ViewModelProvider(this).get(CountUpViewModel::class.java)

        viewModel.mTxtTimer.observe(viewLifecycleOwner,{
            mTextViewCountDown!!.text = it
        })

        viewModel.mButtonText.observe(viewLifecycleOwner,{
            mButtonStartPause!!.text = it
        })

        countUpViewModel.mTxtUpTimer.observe(viewLifecycleOwner,{
            mTextViewCountUp!!.text = it
        })
        countUpViewModel.mUpButtonText.observe(viewLifecycleOwner,{
            mUpTimerStartPause!!.text = it
        })

    }



    override fun onStop() {
        super.onStop()

        viewModel.saveTimer( requireContext())
        countUpViewModel.saveCountUpTimer(requireContext())

    }

    override fun onStart() {
        super.onStart()
      viewModel.getTimer(requireContext())
        countUpViewModel.getCountUpTimer(requireContext())


    }

}