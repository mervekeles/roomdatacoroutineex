package com.example.mvvm_livedata_room.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvm_livedata_room.network.Movie


@Entity
    data class DatabaseMovie constructor(
        @PrimaryKey
        val id: Int,
        val title: String?,
        val overview: String?,
        val posterPath: String?)


fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath
            )
    }
}