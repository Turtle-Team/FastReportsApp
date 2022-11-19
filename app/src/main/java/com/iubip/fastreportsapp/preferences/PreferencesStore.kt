package com.iubip.fastreportsapp.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesStore(context: Context?) {

    companion object {
        const val SELECTED_ID = "6377865f5f620ebfce9a07cb"
        const val SELECTED_ID2 = "ID2"
    }

    private var preferences: SharedPreferences =
        context!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

    fun saveSelectedId(str: String) = preferences.edit().putString(SELECTED_ID, str).apply()

    fun getSavedId(): String? = preferences.getString(SELECTED_ID, SELECTED_ID)
}