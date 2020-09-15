package com.ibtikar.apps.task.api

class MainRepository(private val actorsHelper: ActorsHelper) {

    suspend fun getActors(category: String, api_key: String, language: String, page: Int) =
        actorsHelper.getActors(category, api_key, language, page)

    suspend fun getProfilesImg(person_id: Int, api_key: String) =
        actorsHelper.getProfilesImg(person_id, api_key)
}