package bluefox.rajesh.medicalrepresentative.salesModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ActivityOutStandingBinding
import bluefox.rajesh.medicalrepresentative.databinding.ActivitySalesRepHomeBinding
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.CallIntent
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalesRepHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalesRepHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalesRepHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btNewOrder.setOnClickListener {
            CallIntent.goToNewOrderActivity(this, false, this)

        }

        binding.btOrdersLists.setOnClickListener {
            CallIntent.goToOrdersListActivity(this,false,this)
        }

        binding.btOutStanding.setOnClickListener {
            CallIntent.goToOutStandingActivity(this, false, this)
        }


    }

}