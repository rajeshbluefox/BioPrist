package bluefox.rajesh.medicalrepresentative.zSharedPreference

import android.content.Context
import bluefox.rajesh.medicalrepresentative.zConstants.Constants

object LoginData {
    private const val PREFS_NAME = "MedicalRepresentativeLoginData"

    fun putUserLoginStatus(context: Context, value: Boolean) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(Constants.LOGIN_STATUS, value).apply()
    }

    fun getUserLoginStatus(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(Constants.LOGIN_STATUS, false)
    }

    fun saveUserName(context: Context, value: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(Constants.USER_NAME, value).apply()
    }

    fun getUserName(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(Constants.USER_NAME, "EMPTY")?:"EMPTY"
    }

    fun saveUserId(context: Context, value: Int) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(Constants.REP_USER_ID, value).apply()
    }

    fun getUserId(context: Context): Int {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getInt(Constants.REP_USER_ID, -1)
    }

    fun saveRepresentativeId(context: Context, value: Int) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(Constants.USER_PHONE, value).apply()
    }

    fun getRepresentativeId(context: Context): Int {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getInt(Constants.USER_PHONE, -1)
    }



    fun clear(context: Context) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear().apply()
    }
}
