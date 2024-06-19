package bluefox.rajesh.medicalrepresentative.zCommonFuntions

import android.app.Activity
import android.content.Context
import android.content.Intent
import bluefox.rajesh.medicalrepresentative.homeModule.HomeActivity
import bluefox.rajesh.medicalrepresentative.homeModule.ProfileActivity
import bluefox.rajesh.medicalrepresentative.loginModule.LoginActivity

object CallIntent {
    fun goToHomeActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToProfileActivity(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, ProfileActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

    fun goToLoginActivityandKillMe(context: Context, killMe: Boolean, activity: Activity) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
        if (killMe) activity.finish()
    }

}
