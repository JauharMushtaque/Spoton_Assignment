package com.example.spotonassignment.entities
import com.google.gson.annotations.SerializedName


data class CryptoItem (@SerializedName("data") var data: ArrayList<Data> = arrayListOf(),
                       @SerializedName("timestamp" ) var timestamp : Double? = null )