package com.ibtikar.apps.task.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ibtikar.apps.task.api.MainRepository
import com.ibtikar.apps.task.utils.Resource
import kotlinx.coroutines.Dispatchers


class ActorsViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getActors(
        category: String,
        api_key: String,
        language: String,
        page: Int
    ) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = mainRepository
                        .getActors(category, api_key, language, page)
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}