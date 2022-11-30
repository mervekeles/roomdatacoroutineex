package com.example.mvvm_livedata_room

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.mvvm_livedata_room.database.Plant

@BindingAdapter("plantImage")
fun ImageView.setPlantImage(item: Plant){
    setImageResource(
        when (item.id.toInt() % 10) {
            0 -> R.drawable.plant_0
            1 -> R.drawable.plant_1
            2 -> R.drawable.plant_2
            3 -> R.drawable.plant_3
            4 -> R.drawable.plant_4
            5 -> R.drawable.plant_5
            6 -> R.drawable.plant_6
            7 -> R.drawable.plant_7
            8 -> R.drawable.plant_8
           else -> R.drawable.plant_9
        })
}