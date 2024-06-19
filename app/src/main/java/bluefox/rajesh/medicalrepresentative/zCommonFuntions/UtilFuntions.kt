package bluefox.rajesh.medicalrepresentative.zCommonFuntions

import android.content.Context
import android.widget.Toast

object UtilFunctions{
    fun showToast(context: Context,message: String)
    {
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
    }
}