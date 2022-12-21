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


class MovieListAdapter(val clickListener: MovieClickListener, val addToFavorites: (Movie) -> Unit): ListAdapter<Movie, MovieListAdapter.ItemViewHolder>(MovieDiffCallback()) {

         class ItemViewHolder(val binding:ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
            var favorites = mutableListOf<Movie>()

            companion object{
                private const val TAG = "Adapter"
                fun from(parent: ViewGroup): ItemViewHolder{
                    val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                    return ItemViewHolder(binding)
                }
            }

                fun bind(movie: Movie, clickListener: MovieClickListener, addToFavorites: (Movie) -> Unit) {
                    binding.movie = movie
                    binding.clickListener = clickListener
                    val isFav = checkIfFavorite(movie)
                    binding.isfavorite = isFav
                    binding.addtofavBtn.setOnClickListener {
                        addToFavorites(movie)


                         }
                    binding.executePendingBindings()

            }
             private fun getFavorites()  {
                 val currentUser = Firebase.auth.currentUser
                 if (currentUser != null) {
                     Firebase.firestore.collection("users").document(currentUser.uid)
                         .collection("favorites").get()
                         .addOnSuccessListener { documentSnapshots ->
                             if (!documentSnapshots.isEmpty()) {
                                 Log.d(TAG, "DocumentSnapshot data: ${documentSnapshots.documents}")

                                     for (snapshot in documentSnapshots) {
                                         favorites.add(snapshot.toObject<Movie>())
                                     }


                             } else {
                                 Log.d(TAG, "No such documents")

                             }
                         }
                         .addOnFailureListener { exception ->
                             Log.d(TAG, "get failed with ", exception)
                         }
                 }
             }

             fun checkIfFavorite(movie: Movie):Boolean{
                 getFavorites()
                 for (fav in favorites) {
                     if (fav.id == movie.id) {
                         Log.d(TAG, "Movie is in favorites: ${fav.id}")
                         return true
                     }
                 }
                 return false
             }

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ItemViewHolder {
            return MovieListAdapter.ItemViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val movie = getItem(position)
            holder.bind(movie, clickListener,addToFavorites)


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

