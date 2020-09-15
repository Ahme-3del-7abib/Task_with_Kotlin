package com.ibtikar.apps.task.api

class ActorsHelper(private val actorInterface: ActorsInterface) {

    suspend fun getActors(category: String, api_key: String, language: String, page: Int) =
        actorInterface.getActors(category, api_key, language, page)

    suspend fun getProfilesImg(person_id: Int, api_key: String) =
        actorInterface.getProfilesImg(person_id, api_key)

}