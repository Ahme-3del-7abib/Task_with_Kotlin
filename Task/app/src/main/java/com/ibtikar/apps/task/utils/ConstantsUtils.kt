package com.ibtikar.apps.task.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager

class ConstantsUtils {

    companion object {
        const val BASE_URL: String = "https://api.themoviedb.org"
        const val BASE_IMAGE_SOURCE: String = "http://image.tmdb.org/t/p/w185"
        const val CATEGORY: String = "popular"
        const val API_KEY: String = "737fa690e0b082a189535d1b1df6614f"
        const val LANGUAGE: String = "en-US"
        const val PAGE: Int = 1


        fun isNetworkConnected(activity: Activity): Boolean {
            val cm =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        }
    }


}