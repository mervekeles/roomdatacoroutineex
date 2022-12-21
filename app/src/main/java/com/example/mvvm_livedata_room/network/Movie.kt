package com.example.mvvm_livedata_room.network

import com.squareup.moshi.Json

data class ResultResponse(val results : List<Movie>)
data class Movie(
    val id: Int = 0,
    @Json(name = "original_title") val title: String? = null,
    val overview: String? = null,
    @Json(name = "poster_path")  val posterPath:String? = null, val isFav: Boolean = false){

}
