package bluefox.rajesh.medicalrepresentative.salesModule.newOrder

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.databinding.ActivityCustomerDetailsBinding
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.ProductStockData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SelectedData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.AddedItemsAdapter
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.CollectOutstandingDialog
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.CallIntent
import bluefox.rajesh.medicalrepresentative.zSharedPreference.DatabaseHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerDetailsBinding

    private lateinit var collectOutstandingDialog: CollectOutstandingDialog

    private lateinit var addedItemsAdapter: AddedItemsAdapter

    private val dbHelper = DatabaseHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClickListeners()
    }

    override fun onResume() {
        super.onResume()

        setData()
    }

    private fun initViews() {
        collectOutstandingDialog =
            CollectOutstandingDialog(layoutInflater, this, ::onCollectSubmitClicked)
    }

    private fun onCollectSubmitClicked() {

    }

    private fun setData() {
        binding.tvCustomerNameValue.text = SelectedData.salesCustomerData.customerName

        val addedItemsList =
            dbHelper.getMedicinesByCustomerId(SelectedData.salesCustomerData.customerId)
        if (addedItemsList.isEmpty()) {
            binding.tvTitleAddedItems.visibility = View.GONE
            binding.btEditItem.visibility = View.GONE
        } else {
            initAddedItemsRecyclerView(addedItemsList)
        }
    }

    private fun onClickListeners() {
        binding.btAddItem.setOnClickListener {
            CallIntent.goToAddItemActivity(this, false, this)

        }

        binding.btOutStanding.setOnClickListener {
            CallIntent.goToOutStandingActivity(this, false, this)
//            collectOutstandingDialog.openCollectOutstandingDialog()

        }
        binding.btBack.setOnClickListener {
            finish()
        }

    }

    private fun initAddedItemsRecyclerView(addedItemsList: ArrayList<ProductStockData>) {

        addedItemsAdapter = AddedItemsAdapter(addedItemsList, this, ::onProductClicked)

        binding.rvAddedItems.apply {
            layoutManager = LinearLayoutManager(
                this@CustomerDetailsActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = addedItemsAdapter
        }

    }

    private fun onProductClicked() {

    }

}