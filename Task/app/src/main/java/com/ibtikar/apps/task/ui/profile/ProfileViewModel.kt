package com.ibtikar.apps.task.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ibtikar.apps.task.api.MainRepository
import com.ibtikar.apps.task.utils.Resource
import kotlinx.coroutines.Dispatchers

class ProfileViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getProfilesImg(
        id: Int,
        api_key: String

    ) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))
        try {
            emit(
                Resource.success(
                    data = mainRepository
                        .getProfilesImg(id, api_key)
                )
            )
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}