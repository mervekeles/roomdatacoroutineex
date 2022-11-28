package com.example.mvvm_livedata_room.plantdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentPlantDetailBinding
import com.example.mvvm_livedata_room.databinding.FragmentPlantListBinding

class PlantDetailFragment: Fragment() {

    val args: PlantDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentPlantDetailBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_plant_detail, container, false)
        val application = requireNotNull(this.activity).application



        val plantDetailViewModel: PlantDetailViewModel by viewModels(){ PlantDetailViewModelFactory(args.plantid, application)}
        binding.vm = plantDetailViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}