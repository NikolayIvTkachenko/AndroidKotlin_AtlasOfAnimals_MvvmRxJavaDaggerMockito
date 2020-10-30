package com.rsh_engineering.tkachenkoni.atlasofanimals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.rsh_engineering.tkachenkoni.atlasofanimals.R
import com.rsh_engineering.tkachenkoni.atlasofanimals.model.AnimalModel
import com.rsh_engineering.tkachenkoni.atlasofanimals.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var listAdapter: ItemAnimalAdapter

    private val animalListDataObserver = Observer<List<AnimalModel>>{ list ->
        list?.let {
            rv_animals.visibility = View.VISIBLE
            listAdapter.updateData(it as ArrayList<AnimalModel>)
        }

    }

    private val loadingLiveDataObserver = Observer<Boolean> {isLoading ->
        pb_loading.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading){
            tv_error.visibility = View.GONE
            rv_animals.visibility = View.GONE
        }
    }

    private val loadErrorLiveDataObserver = Observer<Boolean> {isError ->

        tv_error.visibility = if(isError) View.VISIBLE else View.GONE

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }


    fun initUI(){
        setupAdapter()
        viewObservableSetup()
        viewSetup()
    }

    fun setupAdapter(){
        listAdapter = ItemAnimalAdapter(requireContext())
    }

    fun viewObservableSetup(){
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.animals.observe(viewLifecycleOwner, animalListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, loadErrorLiveDataObserver)
        viewModel.refresh()

    }

    fun viewSetup(){

        rv_animals.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        refresh_layout.setOnRefreshListener {
            rv_animals.visibility = View.GONE
            tv_error.visibility = View.GONE
            pb_loading.visibility = View.VISIBLE
            //viewModel.refresh()
            viewModel.hardRefresh()
            refresh_layout.isRefreshing = false
        }
    }

}