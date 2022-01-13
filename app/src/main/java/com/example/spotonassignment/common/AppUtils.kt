package com.example.spotonassignment.common

import android.util.Log
import com.example.spotonassignment.entities.CryptoItem
import com.example.spotonassignment.entities.Data
import com.google.gson.Gson

class AppUtils {
    companion object{
        private const val TAG = "JAUHAR_AppUtils"
        fun roundToTwodigitsAfterPoint(result: String): String {
            val value = result.toDouble()
            val roundOff = Math.round(value * 100.0) / 100.0
            return roundOff.toString()
        }

        fun getRawResponse(): List<Data> {
            val cryptoItem: CryptoItem =
                Gson().fromJson<CryptoItem>(Constants.rawResponse, CryptoItem::class.java)
            Log.d(TAG, "getRawResponse: " + cryptoItem.toString())
            val cryptoData = cryptoItem.data
            return cryptoData
        }
    }
}