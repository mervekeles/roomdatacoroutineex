package com.example.mvvm_livedata_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mvvm_livedata_room.databinding.FragmentMainScreenBinding
import com.example.mvvm_livedata_room.databinding.FragmentMovieDetailBinding
import com.example.mvvm_livedata_room.movielist.MovieListFragmentDirections

class MainFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentMainScreenBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_screen, container, false)

        binding.seeListBtn.setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_movieListFragment)
        }

        return binding.root
    }
}