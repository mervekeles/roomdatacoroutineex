package com.example.mvvm_livedata_room.plantlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_livedata_room.database.Plant
import com.example.mvvm_livedata_room.R

class PlantListAdapter: RecyclerView.Adapter<PlantListAdapter.ItemViewHolder>() {
    var data =  listOf<Plant>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

        class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val nameView= view.findViewById<TextView>(R.id.plant_name)
            val plantImage= view.findViewById<ImageView>(R.id.imageView)
            private var plantid: Int = 0
            init {
                view.setOnClickListener {
                    val action =
                        PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(
                            plantid
                        )
                    Navigation.findNavController(view).navigate(action)
                }
            }

                fun bind(plant: Plant) {
                    plantid = plant.id
                    nameView.text = plant.plantName
//                plantImage.setImageResource(when (plant.id % 10) {
//                    0 -> R.drawable.plant_0
//                    1 -> R.drawable.plant_1
//                    2 -> R.drawable.plant_2
//                    3 -> R.drawable.plant_3
//                    4 -> R.drawable.plant_4
//                    5 -> R.drawable.plant_5
//                    6 -> R.drawable.plant_6
//                    7 -> R.drawable.plant_7
//                    8 -> R.drawable.plant_8
//                    9 -> R.drawable.plant_9
//                    else -> R.drawable.plant_0
//                })

            }

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListAdapter.ItemViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_plant, parent,false)
            return PlantListAdapter.ItemViewHolder(layout)
        }

        override fun onBindViewHolder(holder: PlantListAdapter.ItemViewHolder, position: Int) {
            val plant = data[position]
            holder.bind(plant)

        }

        override fun getItemCount() =  data.size

}