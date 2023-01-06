package com.example.mvvm_livedata_room

import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.example.mvvm_livedata_room.authentication.CreateAccountViewModel
import com.example.mvvm_livedata_room.databinding.FragmentMainScreenBinding
import com.example.mvvm_livedata_room.databinding.FragmentMovieDetailBinding
import com.example.mvvm_livedata_room.movielist.MovieListFragmentDirections

class MainFragment:Fragment() {

    val viewModel: MainFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  binding : FragmentMainScreenBinding
                = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_screen, container, false)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        logout()

                        true
                    }
                    else -> false
            }
        } }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.seeListBtn.setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_movieListFragment)
        }
        binding.signUpBtn.setOnClickListener {
            this.findNavController().navigate(R.id.action_mainFragment_to_emailPasswordFragment)
        }

        return binding.root
    }

    fun logout(){
        viewModel.logout()
        //this.findNavController().navigate(R.id.action_mainFragment_to_emailPasswordFragment)
    }
   /* override fun onStart() {
        super.onStart()
        Log.d("Main Frag", "on start ")
        if(viewModel.checkIfUserAuthenticated()){
            Log.d("Main Frag", "authenticated")
            this.findNavController().navigate(R.id.action_mainFragment_to_movieListFragment)
        }




   }*/
}