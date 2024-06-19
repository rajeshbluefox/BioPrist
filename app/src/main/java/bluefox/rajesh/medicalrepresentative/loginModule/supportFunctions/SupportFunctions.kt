package bluefox.rajesh.medicalrepresentative.loginModule.supportFunctions

import android.content.Context
import android.view.View
import bluefox.rajesh.medicalrepresentative.databinding.ActivityLoginBinding
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions

class SupportFunctions(val context: Context,
                       private val loginBinding: ActivityLoginBinding,
                       private val listener: (userName: String,password: String) -> Unit)
{

}