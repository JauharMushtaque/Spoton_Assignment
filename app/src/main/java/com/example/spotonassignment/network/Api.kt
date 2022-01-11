package com.example.spoton_assignment.network

import com.example.spotonassignment.model.CryptoItem
import retrofit2.Call
import retrofit2.http.GET

interface Api {
//    https://jsonkeeper.com/b/YGDY
    companion object{
//        val mBaseUrl: String  = "https://api.coincap.io/v2/"
    val mBaseUrl: String  = "https://jsonkeeper.com/"
    }

    @GET("b/YGDY"/*"assets?limit=20&offset=0"*/)
    fun getCryptoitem(): Call<CryptoItem>

}