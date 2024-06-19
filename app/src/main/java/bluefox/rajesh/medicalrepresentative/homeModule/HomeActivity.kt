package bluefox.rajesh.medicalrepresentative.homeModule

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ActivityHomeBinding
import bluefox.rajesh.medicalrepresentative.homeModule.apiFunctions.HomeViewModel
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.DoctorsFragment
import bluefox.rajesh.medicalrepresentative.homeModule.historyFragment.HistoryFragment
import bluefox.rajesh.medicalrepresentative.loginModule.LoginViewModel
import bluefox.rajesh.medicalrepresentative.loginModule.supportFunctions.LogoutDialog
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.CallIntent
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var loginViewModel: LoginViewModel


    private lateinit var logoutDialog: LogoutDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)

        Log.e("Test","Hi, ${LoginData.getUserName(this)}")

        initViews()
        fillDoctorsScreen()
        onClickListeners()
    }

    private fun initViews()
    {
        logoutDialog= LogoutDialog(layoutInflater,this,::onLogoutClicked)

        updateLoginStatus(2)
    }

    private fun onLogoutClicked()
    {

        updateLoginStatus(0)

        LoginData.putUserLoginStatus(this,false)
        CallIntent.goToLoginActivityandKillMe(this,true,this)

    }

    private fun onClickListeners() {

        binding.doctorsBT.setOnClickListener {
            fillDoctorsScreen()
        }

        binding.historyBT.setOnClickListener {
            fillHistroyScreen()
        }

        binding.btLogout.setOnClickListener {
            logoutDialog.openLogoutDialog()
        }

        binding.btProfile.setOnClickListener {
            CallIntent.goToProfileActivity(this,false,this)
        }

    }

    private fun fillDoctorsScreen() {

        binding.titleBar.text="Hi, ${LoginData.getUserName(this)}"
        binding.icDoctor.setImageResource(R.drawable.ic_doctor_selected)
        binding.icHistory.setImageResource(R.drawable.ic_history)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, DoctorsFragment())
            .commit()
    }

    private fun fillHistroyScreen() {
        binding.titleBar.text="Hi, ${LoginData.getUserName(this)}"

        binding.icDoctor.setImageResource(R.drawable.ic_doctor)
        binding.icHistory.setImageResource(R.drawable.ic_history_selected)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFragment, HistoryFragment())
            .commit()
    }

    private fun updateLoginStatus(status: Int)
    {
        val userId= LoginData.getUserId(this)
        loginViewModel.resetUpdateLoginStatus()
        loginViewModel.updateLoginStatus(userId,status)

        updateLoginStatusObserver(status)
    }

    private fun updateLoginStatusObserver(status: Int)
    {
        loginViewModel.updateLoginStatusResponse().observe(this){
            if(it.code!=195)
            {
                if(it.code==200)
                {
                    if(status==0) {
                        UtilFunctions.showToast(this, "Logout Status Update Suces")
                    }else{
                        UtilFunctions.showToast(this, "Login Status Update Suces")
                    }
                }
            }
        }
    }

}