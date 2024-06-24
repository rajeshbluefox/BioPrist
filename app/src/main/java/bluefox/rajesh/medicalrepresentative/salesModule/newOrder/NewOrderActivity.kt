package bluefox.rajesh.medicalrepresentative.salesModule.newOrder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.R
import bluefox.rajesh.medicalrepresentative.databinding.ActivityHomeBinding
import bluefox.rajesh.medicalrepresentative.databinding.ActivityNewOrderBinding
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.VisitData
import bluefox.rajesh.medicalrepresentative.homeModule.historyFragment.VisitHistoryAdapter
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SalesCustomerData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.NewOrdersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewOrderBinding

    private lateinit var newOrdersAdapter: NewOrdersAdapter
    private var customerList: ArrayList<SalesCustomerData> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun initRecyclerView(customers: ArrayList<SalesCustomerData>) {

        newOrdersAdapter = NewOrdersAdapter(customers, this, ::onCancelClicked)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@NewOrderActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = newOrdersAdapter
        }

    }

    private fun onCancelClicked(salesCustomerData: SalesCustomerData)
    {

    }
}
