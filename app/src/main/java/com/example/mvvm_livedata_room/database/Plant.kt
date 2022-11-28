package com.example.mvvm_livedata_room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("plant_common_name") @ColumnInfo(name = "plant_name") val plantName: String?,
    @SerializedName("family_name")@ColumnInfo(name = "plant_family") val plantFamily: String?)
