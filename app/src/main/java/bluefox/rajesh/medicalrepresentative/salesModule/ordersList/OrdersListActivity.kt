package bluefox.rajesh.medicalrepresentative.salesModule.ordersList

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ActivityCustomerDetailsBinding
import bluefox.rajesh.medicalrepresentative.databinding.ActivityOrdersListBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.MedicineData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.AddedItemsAdapter
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zSharedPreference.DatabaseHelper
import com.bumptech.glide.util.Util

class OrdersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersListBinding

    private lateinit var addedItemsAdapter: AddedItemsAdapter

    val dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        onClickListeners()
    }

    private fun onClickListeners() {
        binding.btBack.setOnClickListener {
            finish()
        }
    }

    private fun setData()
    {
        val addedItems = dbHelper.getAllMedicines()

        if(addedItems.isEmpty())
        {
            UtilFunctions.showToast(this,"No Items Found")
        }else{
            initAddedItemsRecyclerView(addedItems)
        }
    }

    private fun initAddedItemsRecyclerView(addedItemsList: ArrayList<MedicineData>) {

        addedItemsAdapter = AddedItemsAdapter(addedItemsList, this, ::onProductClicked)

        binding.rvAddedItems.apply {
            layoutManager = LinearLayoutManager(
                this@OrdersListActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = addedItemsAdapter
        }

    }

    private fun onProductClicked()
    {

    }

}