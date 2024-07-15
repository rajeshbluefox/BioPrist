package bluefox.rajesh.medicalrepresentative.salesModule.newOrder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bluefox.rajesh.medicalrepresentative.databinding.ActivityAddItemBinding
import bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions.SalesRepAPIFunctions
import bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions.SalesRepViewModel
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.MedicineData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.ProductStockData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SelectedData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.AddQuantityDialog
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.supportFunctions.MedicineItemAdapter
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions
import bluefox.rajesh.medicalrepresentative.zSharedPreference.DatabaseHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    private lateinit var medicineItemAdapter: MedicineItemAdapter
    private var medicineDataList: ArrayList<MedicineData> = ArrayList()

    private lateinit var addQuantityDialog: AddQuantityDialog

    private val dbHelper = DatabaseHelper(this)

    private lateinit var salesRepViewModel: SalesRepViewModel
    private lateinit var salesRepAPIFunctions: SalesRepAPIFunctions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btBack.setOnClickListener {
            finish()
        }
    }

    private fun initViews() {
        addQuantityDialog = AddQuantityDialog(layoutInflater, this, ::onQuantitySubmitClicked)

        salesRepViewModel = ViewModelProvider(this)[SalesRepViewModel::class.java]
        salesRepAPIFunctions = SalesRepAPIFunctions(
            salesRepViewModel,
            this,
            this,
            onCustomersListFetched = {},
            ::onProductsListFetched,
            onOutstandingListFetched = {}
        )

        salesRepAPIFunctions.getProductsList()
    }

    private fun onProductsListFetched(productsList: ArrayList<ProductStockData>) {
        initProductsRV(productsList)
    }


    private fun onQuantitySubmitClicked(quantity: String) {
        addQuantityDialog.close()
        SelectedData.medicineData.quantity = quantity
        if (dbHelper.insertMedicineData(SelectedData.medicineData))
            UtilFunctions.showToast(this, "Item Added")
        else
            UtilFunctions.showToast(this, "Failed to add Item")

    }

    private fun fillDummyItems() {
        val medicineData1 = MedicineData()
        medicineData1.medicineName = "Dolo"
        medicineData1.rate = "22"
        medicineData1.mrp = "55"
        medicineData1.stock = "23.2"
        medicineDataList.add(medicineData1)

        val medicineData2 = MedicineData()
        medicineData2.medicineName = "Saridon"
        medicineData2.rate = "123"
        medicineDataList.add(medicineData2)

        val medicineData3 = MedicineData()
        medicineData3.medicineName = "TusQ"
        medicineData3.mrp = "225"
        medicineDataList.add(medicineData3)

        val medicineData4 = MedicineData()
        medicineData4.medicineName = "Paracetamol"
        medicineData4.stock = "150"
        medicineDataList.add(medicineData4)

//        initRecyclerView(medicineDataList)


    }

    private fun initProductsRV(medicineData: ArrayList<ProductStockData>) {

        medicineItemAdapter = MedicineItemAdapter(medicineData, this, ::onAddQuantityClicked)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@AddItemActivity,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = medicineItemAdapter
        }

    }

    private fun onAddQuantityClicked(medicineData: ProductStockData) {
        SelectedData.medicineData = medicineData

        if (dbHelper.insertMedicineData(medicineData))
            UtilFunctions.showToast(this, "Item Added")
        else
            UtilFunctions.showToast(this, "Failed to add Item")

//        SelectedData.medicineData = medicineData
//        addQuantityDialog.openAddQuantityDialog(medicineData.quantityType)
    }
}