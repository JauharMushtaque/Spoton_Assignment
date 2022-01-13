package com.example.spotonassignment.data.remote

import com.example.spotonassignment.entities.CryptoItem
import retrofit2.Call
import retrofit2.http.GET

interface Api {
//    static webservice when getting error "You exceeded your 200 request(s) rate limit of your FREE plan"
//    in webservice given "https://api.coincap.io/v2/assets?limit=20&offset=0"
//    Mock web service to test https://jsonkeeper.com/b/YGDY
    companion object{
        val mBaseUrl: String  = "https://api.coincap.io/v2/"
    }

    @GET("assets?limit=20&offset=0")
    fun getCryptoitem(): Call<CryptoItem>

}