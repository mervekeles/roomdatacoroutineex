package com.example.mvvm_livedata_room.plantlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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


        val plantDAO = PlantDatabase.getInstance(application).plantDao()
        val plantListViewModel: PlantListViewModel by viewModels(){ PlantListViewModelFactory(plantDAO,application) }

        //val adapter = PlantListAdapter()
        val adapter = PlantListAdapter(PlantClickListener { plantID: Long ->
            Toast.makeText(context, "$plantID clicked", Toast.LENGTH_SHORT).show()
            plantListViewModel.onPlantClicked(plantID)
        })


        binding.rv.adapter = adapter

        plantListViewModel.populateData()
        plantListViewModel.fetchPlantsFromDB().observe(viewLifecycleOwner, Observer{ plants ->
            plants?.let {
                Log.d("Activity", "plants now ${plants.size}")

                //adapter.data = plants
                adapter.submitList(plants)


            }})


        plantListViewModel.navigateToPlantDetail.observe(viewLifecycleOwner, Observer{plants -> plants?.let{
            this.findNavController().navigate(PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(it))

            plantListViewModel.doneNavigating()
        }})

        binding.lifecycleOwner = this

        return binding.root
    }
}