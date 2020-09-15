package com.ibtikar.apps.task.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibtikar.apps.task.api.ActorsHelper
import com.ibtikar.apps.task.api.MainRepository
import com.ibtikar.apps.task.ui.main.ActorsViewModel
import com.ibtikar.apps.task.ui.profile.ProfileViewModel

class ViewModelFactory(private val apiHelper: ActorsHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ActorsViewModel::class.java)) {
            return ActorsViewModel(MainRepository(apiHelper)) as T

        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}