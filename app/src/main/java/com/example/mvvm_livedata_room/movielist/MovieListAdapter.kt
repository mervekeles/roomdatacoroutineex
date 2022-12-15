package com.example.mvvm_livedata_room.movielist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.ListItemMovieBinding
import com.example.mvvm_livedata_room.network.Movie
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class MovieListAdapter(val clickListener: MovieClickListener): ListAdapter<Movie, MovieListAdapter.ItemViewHolder>(MovieDiffCallback()) {

         class ItemViewHolder(val binding:ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

            companion object{
                private const val TAG = "Adapter"
                fun from(parent: ViewGroup): ItemViewHolder{
                    val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                    return ItemViewHolder(binding)
                }
            }

                fun bind(movie: Movie, clickListener: MovieClickListener) {
                    binding.movie = movie
                    binding.clickListener = clickListener
                    binding.executePendingBindings()

            }

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ItemViewHolder {
            return MovieListAdapter.ItemViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val movie = getItem(position)
            holder.bind(movie, clickListener)


        }



}

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class MovieClickListener(val clickListener: (movieID: Int) -> Unit){
    fun onClick(movie: Movie) = clickListener(movie.id)
}

