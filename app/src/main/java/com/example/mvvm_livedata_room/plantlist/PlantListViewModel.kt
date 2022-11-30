package com.example.mvvm_livedata_room.plantlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.database.Plant
import com.example.mvvm_livedata_room.database.PlantDAO
import com.example.mvvm_livedata_room.database.PlantDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class PlantListViewModel(val plantDAO: PlantDAO, application: Application) : AndroidViewModel(application) {
    var app = application

    private val _navigateToPlantDetail = MutableLiveData<Long?>()

    val navigateToPlantDetail
        get() =  _navigateToPlantDetail

    fun populateData() {
        viewModelScope.launch {
            plantDAO.deleteAll()
            prePopulateData()
        }
    }
    private suspend fun prePopulateData() {

        val jsonString = app.resources.openRawResource(R.raw.plants).bufferedReader().use {
            it.readText()
        }
        val typeToken = object : TypeToken<List<Plant>>() {}.type
        val plants = Gson().fromJson<List<Plant>>(jsonString, typeToken)
        for (p in plants){
            Log.v("in viewmodelVM", "${p}")
        }

        plantDAO.insertAll(plants)

    }

    fun fetchPlantsFromDB(): LiveData<List<Plant>> {
        return plantDAO.getAll()
    }

    fun onPlantClicked(id: Long){
        _navigateToPlantDetail.value = id
    }

    fun doneNavigating(){
        _navigateToPlantDetail.value = null
    }

}


class PlantListViewModelFactory(private val dataSource: PlantDAO, private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlantListViewModel::class.java)) {
            return PlantListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}