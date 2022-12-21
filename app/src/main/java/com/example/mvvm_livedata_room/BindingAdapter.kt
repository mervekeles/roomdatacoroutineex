package com.example.mvvm_livedata_room

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_livedata_room.movielist.MovieApiStatus
import com.example.mvvm_livedata_room.movielist.MovieListAdapter
import com.example.mvvm_livedata_room.network.Movie


private const val BASE_URL =
    "https://image.tmdb.org/t/p/w500/"

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgPath = BASE_URL + imgUrl
        Log.v("BindingAdapter","$imgPath")
        val imgUri =
            imgPath.toUri().buildUpon().scheme("https").build()



        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_loading_image)
        }
        MovieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }

        else -> {statusImageView.visibility = View.GONE}
    }

}

@BindingAdapter("setFavoriteImg")
fun bindFavoriteImage(favoriteImageButton: ImageButton,
                     isFavorite: Boolean) {
    if (isFavorite) {
        favoriteImageButton.setImageResource(R.drawable.ic_baseline_favorite_24_red)
    }
    else{
        favoriteImageButton.setImageResource(R.drawable.ic_baseline_favorite_24)
    }
}