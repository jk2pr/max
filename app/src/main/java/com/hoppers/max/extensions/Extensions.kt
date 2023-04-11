package com.hoppers.max.extensions

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.hoppers.max.MainActivity

fun MainActivity.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "max")