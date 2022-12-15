package com.example.mvvm_livedata_room.moviedetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mvvm_livedata_room.network.Movie
import com.example.mvvm_livedata_room.network.MoviesApi
import kotlinx.coroutines.launch

class MovieDetailViewModel(movieKey: Int, application: Application) : AndroidViewModel(application) {
    //we extend from AndroidViewModel instead of ViewModel because it's the version that can reference the application context to instantiate the database in a lifecycle-aware way.


    val app = application

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    init {

        viewModelScope.launch {
            Log.v("GetMovie by ID", " ${movieKey}")
            try {
                _movie.value = MoviesApi.retrofitService.getMovie(movieKey)
                Log.v("GetMovie by ID", "Success: ${movie.value?.title}")
            } catch (e: Exception) {
                Log.v("GetMovie by ID", "Failure: ${e.message}")

            }
        }
    }
}

    class MovieDetailViewModelFactory(private val movieKey: Int, private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
                return MovieDetailViewModel(movieKey, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
