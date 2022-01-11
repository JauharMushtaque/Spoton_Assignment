package com.example.spotonassignment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.spoton_assignment.network.Api
import com.example.spotonassignment.model.CryptoItem
import com.example.spotonassignment.model.Data
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MainRepository @Inject constructor(val api: Api) {
    companion object{
        private const val TAG = "JAUHAR_MainRepository"
    }

    var cryptoData : List<Data> = emptyList()

    fun getUpdatedCrypto(): List<Data> {
        api.getCryptoitem().enqueue(object : Callback<CryptoItem> {
            override fun onResponse(call: Call<CryptoItem>, response: Response<CryptoItem>) {
                val d = Log.d(TAG, "onResponse: ")
                cryptoData = response.body().let { it?.data!! }

            }

            override fun onFailure(call: Call<CryptoItem>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
                cryptoData = getRawResponse()
            }

        })
        return cryptoData
    }


    private fun getRawResponse(): List<Data> {
        val cryptoItem: CryptoItem =
            Gson().fromJson<CryptoItem>(Constants.rawResponse, CryptoItem::class.java)
        Log.d(TAG, "getRawResponse: " + cryptoItem.toString())
        cryptoData = cryptoItem.data
        return cryptoData
    }
}