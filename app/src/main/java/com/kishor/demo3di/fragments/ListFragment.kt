package com.kishor.demo3di.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kishor.demo3di.MainViewModelFactory
import com.kishor.demo3di.R
import com.kishor.demo3di.adapters.MainListAdapter
import com.kishor.demo3di.network.APIService
import kotlinx.android.synthetic.main.list_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    lateinit var mainListAdapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupList()
        setupView()

    }

    private fun setupView() {
       lifecycleScope.launch {
           viewModel.listData.collect {
            mainListAdapter.submitData(it)
           }
       }

    }

    private fun setupList() {
        mainListAdapter = MainListAdapter(requireContext())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mainListAdapter
        }
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider(
                this,
                MainViewModelFactory(APIService.getApiService())
            )[ListViewModel::class.java]
    }

}