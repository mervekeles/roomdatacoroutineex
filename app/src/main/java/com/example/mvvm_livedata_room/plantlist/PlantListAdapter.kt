package com.example.mvvm_livedata_room.plantlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_livedata_room.database.Plant
import com.example.mvvm_livedata_room.R
import com.example.mvvm_livedata_room.databinding.ListItemPlantBinding

class PlantListAdapter(val clickListener: PlantClickListener): ListAdapter<Plant, PlantListAdapter.ItemViewHolder>(PlantDiffCallback()) {
//    var data =  listOf<Plant>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

        class ItemViewHolder(val binding: ListItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {
            //val nameView= view.findViewById<TextView>(R.id.plant_name)
            //val plantImage= view.findViewById<ImageView>(R.id.imageView)
            private var plantid: Long = 0
//            init {
//                view.setOnClickListener {
//                    val action =
//                        PlantListFragmentDirections.actionPlantListFragmentToPlantDetailFragment(
//                            plantid
//                        )
//                    Navigation.findNavController(view).navigate(action)
//                }
//            }
            companion object{
                fun from(parent: ViewGroup): ItemViewHolder{
                    val binding = ListItemPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                    return ItemViewHolder(binding)
                }
            }

                fun bind(plant: Plant, clickListener: PlantClickListener) {
                    plantid = plant.id
                    //nameView.text = plant.plantName
                    binding.plant = plant
                    binding.clickListener = clickListener



            }

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListAdapter.ItemViewHolder {
//            val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item_plant, parent,false)
//            return PlantListAdapter.ItemViewHolder(layout)

            return ItemViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: PlantListAdapter.ItemViewHolder, position: Int) {
            val plant = getItem(position)
            //holder.bind(plant)
            holder.bind(plant, clickListener)

        }


}

class PlantDiffCallback: DiffUtil.ItemCallback<Plant>(){
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }

}

class PlantClickListener(val clickListener: (plantID: Long) -> Unit){
    fun onClick(plant: Plant) = clickListener(plant.id)
}










