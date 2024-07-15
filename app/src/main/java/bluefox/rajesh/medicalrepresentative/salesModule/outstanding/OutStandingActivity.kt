package bluefox.rajesh.medicalrepresentative.salesModule.outstanding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.databinding.ActivityOutStandingBinding
import bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions.SalesRepAPIFunctions
import bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions.SalesRepViewModel
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.CollectOutstandingDialog
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.InvoiceAdapter
import bluefox.rajesh.medicalrepresentative.salesModule.outstanding.supportFunctions.AddInvoiceDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OutStandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOutStandingBinding

    private lateinit var invoiceAdapter: InvoiceAdapter
    private var invoiceDataList: ArrayList<InvoiceData> = ArrayList()

    private lateinit var collectOutstandingDialog: CollectOutstandingDialog

    private lateinit var addInvoiceDialog: AddInvoiceDialog

    private lateinit var salesRepViewModel: SalesRepViewModel
    private lateinit var salesRepAPIFunctions: SalesRepAPIFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOutStandingBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        fillDummyItems()
        onClickListeners()

        initViews()

    }

    private fun initViews() {
        collectOutstandingDialog =
            CollectOutstandingDialog(layoutInflater, this, ::onCollectSubmitClicked)

        addInvoiceDialog=AddInvoiceDialog(layoutInflater,this,::onAddInvoiceClicked)

        salesRepViewModel = ViewModelProvider(this)[SalesRepViewModel::class.java]
        salesRepAPIFunctions = SalesRepAPIFunctions(
            salesRepViewModel,
            this,
            this,
            onCustomersListFetched = {},
            onProductsListFetched = {},
            ::onOutstandingListFetched
        )

        salesRepAPIFunctions.getOutstandingList(0,0)
    }

    private fun onAddInvoiceClicked()
    {

    }

    private fun onCollectSubmitClicked() {

    }

    private fun onClickListeners() {
        binding.btBack.setOnClickListener {
            finish()
        }

        binding.tvCollection.setOnClickListener {
            collectOutstandingDialog.openCollectOutstandingDialog()
        }

        binding.tvInvoiceNoTitleD.setOnClickListener {
            addInvoiceDialog.openAddInvoiceButton()
        }
    }

    private fun fillDummyItems()
    {
       val invoiceData1 = InvoiceData()
        invoiceData1.invoiceNumber="123"
        invoiceDataList.add(invoiceData1)

        val invoiceData2 = InvoiceData()
        invoiceData2.invoiceDate="24-12-24"
        invoiceDataList.add(invoiceData2)

        val invoiceData3 = InvoiceData()
        invoiceData3.invoiceAmount="250"
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)
        invoiceDataList.add(invoiceData3)

        initOutstandingRV(invoiceDataList)


    }

    private fun onOutstandingListFetched(outstandingList: ArrayList<InvoiceData>) {
        initOutstandingRV(outstandingList)
    }

    private fun initOutstandingRV(invoiceData: ArrayList<InvoiceData>) {

        invoiceAdapter = InvoiceAdapter(invoiceData, this, ::onCancelClicked)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@OutStandingActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = invoiceAdapter
        }

    }

    private fun onCancelClicked(invoiceData: InvoiceData)
    {

    }
}

