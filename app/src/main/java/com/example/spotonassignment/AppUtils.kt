package com.example.spotonassignment

class AppUtils {
    companion object{

        fun roundToTwodigitsAfterPoint(result: String): String {
            val value = result.toDouble()
            val roundOff = Math.round(value * 100.0) / 100.0
            return roundOff.toString()
        }
    }
}