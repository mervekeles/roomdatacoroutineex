package com.example.mvvm_livedata_room.plantlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.database.PlantDatabase
import com.example.mvvm_livedata_room.databinding.FragmentPlantListBinding

class PlantListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentPlantListBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plant_list, container, false)

        val application = requireNotNull(this.activity).application

        val adapter = PlantListAdapter()
        binding.rv.adapter = adapter
        binding.lifecycleOwner = this

        val plantDAO = PlantDatabase.getInstance(application).plantDao()
        val plantListViewModel: PlantListViewModel by viewModels(){ PlantListViewModelFactory(plantDAO,application) }
        plantListViewModel.populateData()
        plantListViewModel.fetchPlantsFromDB().observe(viewLifecycleOwner, Observer{ plants ->
            plants?.let {
                Log.d("Activity", "plants now ${plants.size}")

                adapter.data = plants


            }})
        return binding.root
    }
}