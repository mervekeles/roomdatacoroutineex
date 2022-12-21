package com.example.mvvm_livedata_room.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL =
    "https://api.themoviedb.org/3/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MoviesAPIService {
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmOTBkODU1MzZkOGI2MDUwNjMzYTU5YzFiMWI2MTM4ZiIsInN1YiI6IjYxYjhmOTU2ZTI2M2JiMDAxYzRiOWEyNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.hcyGZm_ieY0K7N8z8VyQZJ2KxOjB1rh57NhQBSkRpJQ",
        "Content-Type: application/json;charset=utf-8"
    )
//    @GET("movie/top_rated")
//    fun getMostPopular(): Call<ResultResponse>

    //coroutine added
    @GET("movie/top_rated")
    suspend fun getMostPopular(): ResultResponse

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId:Int): Movie

}

object MoviesApi {
    val retrofitService : MoviesAPIService by lazy {
        retrofit.create(MoviesAPIService::class.java) }
}