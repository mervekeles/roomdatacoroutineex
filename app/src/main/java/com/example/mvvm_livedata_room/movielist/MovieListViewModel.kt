package com.example.mvvm_livedata_room.movielist

import android.app.Application
import androidx.lifecycle.*
import com.example.mvvm_livedata_room.network.Movie
import com.example.mvvm_livedata_room.network.MoviesApi
import kotlinx.coroutines.launch


enum class MovieApiStatus { LOADING, ERROR, DONE }

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies :LiveData<List<Movie>>
        get() = _movies

    private val _navigateToMovieDetail = MutableLiveData<Int?>()

    val navigateToMovieDetail
        get() =  _navigateToMovieDetail

    private fun getMovies() {
        /*MoviesApi.retrofitService.getMostPopular().enqueue(
            object : Callback<ResultResponse> {
                //                override fun onResponse(call: Call<List<Movie>>, response: Response<String>) {
//                    _response.value = response.body()
//                }
//
//                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                    _response.value = "Failure: " + t.message
//                }
                override fun onResponse(call: Call<ResultResponse>, response: Response<ResultResponse>) {
                    _response.value = "Success: ${response.body()?.results}  "                }

                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                    _response.value = "Failure: " + t.message                }

            })
*/

        //coroutine version
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                val response = MoviesApi.retrofitService.getMostPopular()

               _response.value =
                    "Success: ${response.results.size} Movies retrieved"
                _movies.value = response.results
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                _status.value = MovieApiStatus.ERROR
                _movies.value = ArrayList()
            }
        }
    }

    init{
        getMovies()
    }

    fun onMovieClicked(id: Int){
        _navigateToMovieDetail.value = id
    }

    fun doneNavigating(){
        _navigateToMovieDetail.value = null
    }

}


class MovieListViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieListViewModel::class.java)) {
            return MovieListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}