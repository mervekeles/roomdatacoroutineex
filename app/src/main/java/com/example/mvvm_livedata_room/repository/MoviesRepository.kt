package com.example.mvvm_livedata_room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mvvm_livedata_room.database.MoviesDatabase
import com.example.mvvm_livedata_room.database.asDatabaseModel
import com.example.mvvm_livedata_room.database.asDomainModel
import com.example.mvvm_livedata_room.network.Movie
import com.example.mvvm_livedata_room.network.MoviesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val database: MoviesDatabase) {

    val movies: LiveData<List<Movie>> =
    Transformations.map(database.movieDao.getMovies()) {
        it.asDomainModel()
    }

    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            val movies = MoviesApi.retrofitService.getMostPopular()
            database.movieDao.insertAll(movies.asDatabaseModel())

        }
    }
}