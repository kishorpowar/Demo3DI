package com.kishor.demo3di.fragments

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CountUpViewModel: ViewModel() {

    private val customHandler: Handler = Handler()
    private var startTime = 0L
    var timeInMilliseconds = 0L
    var timeSwapBuff = 0L
    var updatedTime = 0L
    private var mTimerRunning = false

    val mTxtUpTimer = MutableLiveData<String>()
     val mUpButtonText = MutableLiveData<String>()

    fun onStartClick(requireContext: Context) {
        if(mTimerRunning){
            mUpButtonText.value="Start"
            resetTimer(requireContext)
            mTimerRunning = false
        }else{
            mTimerRunning = true
            mUpButtonText.value="Reset"
            startTime = System.currentTimeMillis()
            Log.i("onclickms", startTime.toString())
            customHandler.postDelayed(updateTimerThread, 1000)
        }
    }

    private fun resetTimer(requireContext: Context) {

        val prefs = requireContext.getSharedPreferences("prefs1", AppCompatActivity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
        mTxtUpTimer.value = "00:00"
        startTime=0
        customHandler.removeCallbacks(updateTimerThread)
    }



    private val updateTimerThread: Runnable = object : Runnable {
        override fun run() {
            timeInMilliseconds = System.currentTimeMillis() - startTime
            Log.i("Runnablems", timeInMilliseconds.toString())
            updatedTime = timeSwapBuff + timeInMilliseconds
            val timeElapsed: Long = updatedTime//For example
            val hours = (timeElapsed / 3600000).toInt()
            val mins = (timeElapsed - hours * 3600000).toInt() / 60000
            val secs = (timeElapsed - hours * 3600000 - mins * 60000).toInt() / 1000
            val milliseconds = (updatedTime % 1000).toInt()
            Log.i("Timer", java.lang.String.format("%02d", mins).toString() + ":" + java.lang.String.format("%02d", secs))
            mTxtUpTimer.value = java.lang.String.format("%02d", hours).toString()+ ":"+ java.lang.String.format("%02d", mins).toString() + ":"+ java.lang.String.format("%02d", secs)
            customHandler.postDelayed(this, 1000)
        }
    }


    fun saveCountUpTimer(requireContext: Context) {
        val prefs = requireContext.getSharedPreferences("prefs1", AppCompatActivity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putLong("startime", startTime)
        editor.apply()
        mTimerRunning = false
    }

    fun getCountUpTimer(requireContext: Context) {

        val prefs = requireContext.getSharedPreferences("prefs1", AppCompatActivity.MODE_PRIVATE)
        startTime = prefs.getLong("startime", startTime)
        if(startTime!=0L){
            customHandler.postDelayed(updateTimerThread, 1000)
            mTimerRunning = true
            mUpButtonText.value="Reset"
        }
    }


}