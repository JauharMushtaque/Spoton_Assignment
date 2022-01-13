package com.example.spotonassignment.data.repository

import android.util.Log
import android.widget.Toast
import com.example.spotonassignment.common.AppUtils
import com.example.spotonassignment.data.remote.Api
import com.example.spotonassignment.common.Constants
import com.example.spotonassignment.entities.CryptoItem
import com.example.spotonassignment.entities.Data
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(val api: Api) {
    companion object{
        private const val TAG = "JAUHAR_MainRepository"
    }

    var cryptoData : List<Data> = emptyList()

    fun getUpdatedCrypto(): List<Data> {
        api.getCryptoitem().enqueue(object : Callback<CryptoItem> {
            override fun onResponse(call: Call<CryptoItem>, response: Response<CryptoItem>) {
                if(response.isSuccessful && response.body()!=null){
                    Log.d(TAG, "onResponse: ")
                    cryptoData = response.body().let { it?.data!! }
                }else{
                    Log.d(TAG, "error: "+response.errorBody())
                }


            }

            override fun onFailure(call: Call<CryptoItem>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
                cryptoData = AppUtils.getRawResponse()
            }

        })
        return cryptoData
    }

}