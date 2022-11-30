package com.example.mvvm_livedata_room.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

    @Dao
    interface PlantDAO {
        @Insert
        fun insert(plant: Plant)

        @Insert
        suspend fun insertAll(plants: List<Plant>)

        @Query("SELECT * FROM plant")
        fun getAll(): LiveData<List<Plant>>

        @Query("DELETE FROM plant")
        suspend fun deleteAll()

        @Query("SELECT * from plant WHERE id = :key")
        fun get(key: Long): LiveData<Plant>
    }
