package bluefox.rajesh.medicalrepresentative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bluefox.rajesh.medicalrepresentative.databinding.ActivityDummyBinding

class DummyActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDummyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDummyBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


}