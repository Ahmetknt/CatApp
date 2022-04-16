package com.ahmetkanat.catapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class Cat (
    //@ColumnInfo(name = "name")
    var name: String,
    //@ColumnInfo(name = "origin")
    var origin: String,
    //@ColumnInfo(name = "description")
    var description: String,
    //@ColumnInfo(name = "life_span")
    var life_span  : String,
    //@ColumnInfo(name = "wikipedia_url")
    var wikipedia_url : String,
    //@ColumnInfo(name = "dog_friendly")
    var dog_friendly : Int,
    //@ColumnInfo(name = "image")
    var image : Image

):Serializable{
    //@PrimaryKey(autoGenerate = true)
    //var dbId = 0
}