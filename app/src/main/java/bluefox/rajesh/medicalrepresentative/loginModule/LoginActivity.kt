package bluefox.rajesh.medicalrepresentative.loginModule

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ActivityLoginBinding
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.RepresentativeData
import bluefox.rajesh.medicalrepresentative.loginModule.supportFunctions.RepresentativeSpinnerAdapter
import bluefox.rajesh.medicalrepresentative.loginModule.supportFunctions.SupportFunctions
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.CallIntent
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializations()
    }

    private fun initializations() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding.root)

        loginViewModel.reset()


        showLoginLt()
        onClickListeners()
        observer()

    }

    private fun onClickListeners() {
        binding.btLogin.setOnClickListener {

            showPB()

            if (validateDetails()) {
                closeKeyboard()
//                getRepresentativeList()
            }
            else{
                hidePB()
            }
        }

        binding.btContinue.setOnClickListener {
            if (isRepSelected) {
                LoginData.putUserLoginStatus(this,true)
                LoginData.saveUserName(this,mSelectedRepName)
                LoginData.saveRepresentativeId(this,mSelectedRepId)

                CallIntent.goToHomeActivity(this, true, this)
            } else {
                UtilFunctions.showToast(this, "Select Representative Name")
            }
        }
    }



    private fun getRepresentativeList(repId: Int) {
        loginViewModel.getRepresentativeList(repId)
    }


    private fun observer() {
        loginViewModel.getRepresentativeResponse().observe(this) {
            if (it.code != 195) {
                if (it.code == 200) {

                    if(it.representativesList.isNotEmpty())
                    {
                        LoginData.saveUserName(this,it.representativesList[0].RepName)
                        LoginData.saveRepresentativeId(this,it.representativesList[0].RepId!!.toInt())
                        LoginData.putUserLoginStatus(this,true)

                        CallIntent.goToHomeActivity(this, true, this)

                    }else{
                        UtilFunctions.showToast(this,"Invalid Rep")
                    }

//                    hideLoginLt()
//                    initRepresentativeSpinner(it.representativesList)
                }else{
                    UtilFunctions.showToast(this,"Something wrong")
                }
                hidePB()
            }
        }

        loginViewModel.getLoginResponse().observe(this){
            if(it.code!=195)
            {
                if(it.code==200)
                {
                    LoginData.saveUserId(this,it.userId)

                    getRepresentativeList(it.employeeId)
                }else{
                    hidePB()
                    UtilFunctions.showToast(this,"Login Failed")
                }
            }
        }
    }

    var mSelectedRepName = ""
    var mSelectedRepId = -1

    var isRepSelected = false

    private fun initRepresentativeSpinner(
        representativeList: List<RepresentativeData>
    ) {

        val newItem = RepresentativeData(
            -1, -1,
            "Select",
        )

        val newrepresentativeList = listOf(newItem) + representativeList

        val adapter =
            RepresentativeSpinnerAdapter(
                this,
                android.R.layout.simple_spinner_item,
                newrepresentativeList
            )
        binding.spinnerMedRep.adapter = adapter

        binding.spinnerMedRep.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    if (position != 0) {
                        mSelectedRepName = newrepresentativeList[position].RepName
                        mSelectedRepId = newrepresentativeList[position].RepId!!
                        isRepSelected = true
                    } else {
                        isRepSelected = false
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle case when nothing is selected (optional)
                }
            }

    }

    fun validateDetails(): Boolean {
        val mUserName = binding.etUserName.text.toString().trim()
        val mPassword = binding.etMobileNo.text.toString().trim()

        if (mUserName.isEmpty()) {
            UtilFunctions.showToast(this, "UserName Missing")
            return false
        }

        if (mPassword.isEmpty()) {
            UtilFunctions.showToast(this, "Password Missing")
            return false
        }

        loginViewModel.postUserNameandPassword(mUserName,mPassword)

//        if (mUserName == "user1" && mPassword == "123456") {
//            return true
//        } else {
//            UtilFunctions.showToast(this, "Wrong Credentials")
//        }

        return true
    }

    fun showLoginLt() {
        binding.contentLt.visibility = View.VISIBLE
        binding.selectRepLt.visibility = View.GONE
    }

    fun hideLoginLt() {
        binding.contentLt.visibility = View.GONE
        binding.selectRepLt.visibility = View.VISIBLE
    }

    fun closeKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etMobileNo.windowToken, 0)
    }

    fun showPB()
    {
        binding.btLogin.visibility=View.GONE
        binding.progressBar.visibility=View.VISIBLE
    }

    fun hidePB()
    {
        binding.btLogin.visibility=View.VISIBLE
        binding.progressBar.visibility=View.GONE
    }

}