package com.example.taskmanager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {
    private var pref = context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE)

    //onBoard
    fun isUserSeenBoard(): Boolean {
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun userSeen() {
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    //name save
    fun saveName(name: String) {
        pref.edit().putString(SAVE_NAME_KEY, name).apply()
    }

    fun getName(): String? {
        return pref.getString(SAVE_NAME_KEY, "")
    }

    //profile photo save
    fun savePhoto(image: String) {
        pref.edit().putString(SAVE_IMAGE, image).apply()
    }

    fun getPhoto(): String? {
        return pref.getString(SAVE_IMAGE, "")
    }

    companion object {
        const val SHARED_NAME = "task_app"
        const val SEEN_KEY = "seen_key"
        const val SAVE_NAME_KEY = "save_name.key"
        const val SAVE_IMAGE = "save_image.key"
    }
}