package bluefox.rajesh.medicalrepresentative.salesModule.newOrder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.databinding.ActivityNewOrderBinding
import bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions.SalesRepAPIFunctions
import bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions.SalesRepViewModel
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SalesCustomerData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SelectedData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.NewOrdersAdapter
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.CallIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewOrderBinding

    private lateinit var salesRepViewModel: SalesRepViewModel
    private lateinit var salesRepAPIFunctions: SalesRepAPIFunctions


    private lateinit var newOrdersAdapter: NewOrdersAdapter
    private var customerList: ArrayList<SalesCustomerData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClickListeners()
    }

    fun initViews()
    {
        salesRepViewModel = ViewModelProvider(this)[SalesRepViewModel::class.java]
        salesRepAPIFunctions = SalesRepAPIFunctions(
            salesRepViewModel,
            this,
            this,
            ::onCustomersListFetched,
            onProductsListFetched={},
            onOutstandingListFetched = {}
        )

        salesRepAPIFunctions.getCustomerList()
    }

    private fun onClickListeners() {
        binding.btBack.setOnClickListener {
            finish()
        }
    }

    private fun onCustomersListFetched(customersList : ArrayList<SalesCustomerData>)
    {
        initCustomersRV(customersList)
    }


    private fun fillDummyItems()
    {
        val customerData1 = SalesCustomerData()
        customerData1.customerId=1
        customerData1.customerName="AB Medicals"
        customerList.add(customerData1)

        val customerData2 = SalesCustomerData()
        customerData2.customerId=2
        customerData2.customerName="MedPlus"
        customerList.add(customerData2)

        val customerData3 = SalesCustomerData()
        customerData3.customerId=3
        customerData3.customerName="Apollo Pharma"
        customerList.add(customerData3)

        val customerData4 = SalesCustomerData()
        customerData4.customerId=4
        customerData4.customerName="Surya Pharma"
        customerList.add(customerData4)

        val customerData5 = SalesCustomerData()
        customerData5.customerId=5
        customerData5.customerName="AB Medicals"
        customerList.add(customerData5)

//        initRecyclerView(customerList)

    }

    private fun initCustomersRV(customers: ArrayList<SalesCustomerData>) {

        newOrdersAdapter = NewOrdersAdapter(customers, this, ::onItemClicked)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@NewOrderActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = newOrdersAdapter
        }

    }

    private fun onItemClicked(salesCustomerData: SalesCustomerData)
    {
        SelectedData.salesCustomerData=salesCustomerData
        CallIntent.goToCustomerDetailsActivity(this, false, this)

    }
}
