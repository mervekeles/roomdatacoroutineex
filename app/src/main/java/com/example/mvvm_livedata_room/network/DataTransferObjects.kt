package com.example.mvvm_livedata_room.database

import com.example.mvvm_livedata_room.network.ResultResponse


fun ResultResponse.asDatabaseModel(): List<DatabaseMovie> {
        return results.map {
            DatabaseMovie(
                title = it.title,
                id = it.id,
                overview = it.overview,
                posterPath = it.posterPath)
        }
    }

