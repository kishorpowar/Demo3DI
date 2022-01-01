package com.kishor.demo3di.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class CountDownViewModel : ViewModel() {

    private val START_TIME_IN_MILLIS: Long = 600000
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimerRunning = false
    private var mTimeLeftInMillis: Long = 0
    private var mEndTime: Long = 0

    val mTxtTimer = MutableLiveData<String>()
    val mButtonText = MutableLiveData<String>()

      fun onButtonClick(){

                 if (mTimerRunning) {
                 resetTimer()
                 } else {
                 startTimer()
                 }

       }


    fun startTimer() {

        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis

        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                Log.i("TimeSpend", "$minutes:$seconds")
            }

            override fun onFinish() {
                mTimerRunning = false
                updateButtons()
            }
        }.start()

        mTimerRunning = true
        updateButtons()

    }

    private fun updateButtons() {

        if (mTimerRunning) {
            mButtonText.value = "Reset"
        }else{
            mButtonText.value ="Start"
        }

    }

    private fun updateCountDownText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60

        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

        mTxtTimer.value = timeLeftFormatted
    }

    fun saveTimer(requireContext: Context) {

        val prefs: SharedPreferences = requireContext.getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        )
        val editor = prefs.edit()

        editor.putLong("millisLeft", mTimeLeftInMillis)
        editor.putBoolean("timerRunning", mTimerRunning)
        editor.putLong("endTime", mEndTime)

        editor.apply()

        if (mCountDownTimer != null) {
            mCountDownTimer!!.cancel()
        }
    }

    fun getTimer(requireContext: Context) {

        val prefs: SharedPreferences = requireContext.getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        )

        mTimeLeftInMillis =
            prefs.getLong("millisLeft", START_TIME_IN_MILLIS)
        mTimerRunning = prefs.getBoolean("timerRunning", false)

        updateCountDownText()
        updateButtons()

        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0)
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis()
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0
                mTimerRunning = false
                updateCountDownText()
                updateButtons()
            } else {
                startTimer()
            }
        }
    }

    fun resetTimer() {


        mTimeLeftInMillis = START_TIME_IN_MILLIS
        mCountDownTimer?.cancel()
        mTxtTimer.value = "00:00"
        mTimerRunning = false
//        updateCountDownText()
        updateButtons()
    }


}