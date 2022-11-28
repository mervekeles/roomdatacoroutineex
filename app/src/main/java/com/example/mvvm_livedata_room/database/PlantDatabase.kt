package com.example.mvvm_livedata_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDAO

    companion object{
        @Volatile
        private var INSTANCE:PlantDatabase?= null

        fun getInstance(context: Context): PlantDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlantDatabase::class.java,
                        "plant_database").fallbackToDestructiveMigration()
                        .build()
                        INSTANCE = instance
                }
                return instance
            }
        }
    }
}