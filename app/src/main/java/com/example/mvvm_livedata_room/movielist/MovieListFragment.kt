package com.example.mvvm_livedata_room.movielist

import android.os.Bundle
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
import com.example.mvvm_livedata_room.databinding.FragmentMovieListBinding

class MovieListFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentMovieListBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_list, container, false)

        val application = requireNotNull(this.activity).application

        val viewModel: MovieListViewModel by viewModels(){ MovieListViewModelFactory(application) }

        binding.rv.adapter = MovieListAdapter(
        MovieClickListener { movieID: Int ->
            Toast.makeText(context, "$movieID clicked", Toast.LENGTH_SHORT).show()
            viewModel.onMovieClicked(movieID)

        }, addToFavorites = { viewModel.addToFavorites(it) })

        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner, Observer{movies -> movies?.let{
            this.findNavController().navigate(MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(it))

            viewModel.doneNavigating()
        }})

        binding.lifecycleOwner = this




        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.vm = viewModel

     return binding.root
    }
}