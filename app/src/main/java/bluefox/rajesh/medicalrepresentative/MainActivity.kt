package bluefox.rajesh.medicalrepresentative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.CallIntent
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkLoginStatusandNavigate()
    }

    private fun checkLoginStatusandNavigate() {
        Handler(Looper.getMainLooper()).postDelayed({

            if (LoginData.getUserLoginStatus(this)) {
                CallIntent.goToHomeActivity(this, true, this)
            } else {
                CallIntent.goToLoginActivityandKillMe(this, true, this)
            }

        }, 2000)
    }
}