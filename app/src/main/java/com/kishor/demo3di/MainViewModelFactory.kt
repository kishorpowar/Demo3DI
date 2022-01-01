package com.kishor.demo3di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kishor.demo3di.fragments.ListViewModel
import com.kishor.demo3di.network.APIService

class MainViewModelFactory(private val apiService: APIService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}