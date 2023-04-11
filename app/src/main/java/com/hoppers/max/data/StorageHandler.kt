package com.hoppers.max.data

import com.hoppers.max.constants.KEY_TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageHandler @Inject constructor(private val preference: AppPreference) {
    suspend fun save(key:String, value:String){
        preference.setValue(key, value)
    }

    suspend fun getToken(): String = preference.getValue(KEY_TOKEN).orEmpty()

}