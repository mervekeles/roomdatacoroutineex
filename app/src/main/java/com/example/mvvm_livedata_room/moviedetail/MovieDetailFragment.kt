
package com.example.mvvm_livedata_room.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.FragmentMovieDetailBinding

class MovieDetailFragment: Fragment() {

    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentMovieDetailBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false)
        val application = requireNotNull(this.activity).application

        val movieDetailViewModel: MovieDetailViewModel by viewModels(){ MovieDetailViewModelFactory(args.id, application) }
       binding.vm = movieDetailViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}