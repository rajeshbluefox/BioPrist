package bluefox.rajesh.medicalrepresentative.homeModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ActivityHomeBinding
import bluefox.rajesh.medicalrepresentative.databinding.ActivityProfileBinding
import bluefox.rajesh.medicalrepresentative.zSharedPreference.LoginData

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDetails()
        onClickListeners()
    }

    private fun setDetails() {
        binding.tvNameValue.text="Hi, ${LoginData.getUserName(this)}"

    }

    private fun onClickListeners() {
        binding.btBack.setOnClickListener {
            finish()
        }
    }
}