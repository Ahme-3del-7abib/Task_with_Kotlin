package com.ibtikar.apps.task.api

import com.ibtikar.apps.task.utils.ConstantsUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ActorsClient {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantsUtils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val actorsInterface: ActorsInterface = getRetrofit().create(ActorsInterface::class.java)
}
