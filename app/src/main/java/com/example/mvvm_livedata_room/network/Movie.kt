package com.example.mvvm_livedata_room.network

import com.example.mvvm_livedata_room.database.DatabaseMovie
import com.squareup.moshi.Json

data class ResultResponse(val results : List<Movie>)
data class Movie(
    val id: Int = 0,
    @Json(name = "original_title") val title: String? = null,
    val overview: String? = null,
    @Json(name = "poster_path")  val posterPath:String? = null){

}

fun ResultResponse.asDatabaseModel(): List<DatabaseMovie>{
    return results.map{
        DatabaseMovie(id = it.id,
            title = it.title,
        overview = it.overview,
            posterpath = it.posterPath
        )
    }
}
