package com.kishor.demo3di.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.kishor.demo3di.data.PostDataSource
import com.kishor.demo3di.network.APIService

class ListViewModel(private val apiService: APIService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6)) {
        PostDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}