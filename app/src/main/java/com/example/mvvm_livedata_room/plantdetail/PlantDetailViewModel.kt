package com.example.mvvm_livedata_room.plantdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_livedata_room.database.Plant
import com.example.mvvm_livedata_room.database.PlantDAO
import com.example.mvvm_livedata_room.database.PlantDatabase

class PlantDetailViewModel(plantKey: Long, application: Application) : AndroidViewModel(application) {
        //we extend from AndroidViewModel instead of ViewModel because it's the version that can reference the application context to instantiate the database in a lifecycle-aware way.


        val app = application
        private var plantDao: PlantDAO

        private val plant: LiveData<Plant>

        fun getPlant() = plant

        init {
            plantDao = PlantDatabase.getInstance(application).plantDao()
            plant=plantDao.get(plantKey)
        }
    }

    class PlantDetailViewModelFactory(private val plantKey: Long, private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlantDetailViewModel::class.java)) {
                return PlantDetailViewModel(plantKey, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
