package com.ibtikar.apps.task.api

import com.ibtikar.apps.task.pojo.Actors
import com.ibtikar.apps.task.pojo.ActorsProfiles
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ActorsInterface {

    @GET("/3/person/{category}")
    suspend fun getActors(
        @Path("category") category: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int

    ): Actors


    @GET("/3/person/{person_id}/images")
    suspend fun getProfilesImg(
        @Path("person_id") id: Int,
        @Query("api_key") api_key: String

    ): ActorsProfiles
}