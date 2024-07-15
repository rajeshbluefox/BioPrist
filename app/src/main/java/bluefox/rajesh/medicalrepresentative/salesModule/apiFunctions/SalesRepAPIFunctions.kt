package bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.ProductStockData
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.SalesCustomerData
import bluefox.rajesh.medicalrepresentative.zCommonFuntions.UtilFunctions

class SalesRepAPIFunctions(
    salesRepViewModelG: SalesRepViewModel,
    lifecycleOwnerG: LifecycleOwner,
    contextG: Context,
    private val onCustomersListFetched: (customersList : ArrayList<SalesCustomerData>) -> Unit,
    private val onProductsListFetched: (productsList : ArrayList<ProductStockData>) -> Unit,
    private val onOutstandingListFetched: (outstandingList : ArrayList<InvoiceData>) -> Unit
) {
    private val salesRepViewModel = salesRepViewModelG
    private val lifecycleOwner = lifecycleOwnerG
    private val context = contextG

    fun getCustomerList()
    {
        salesRepViewModel.resetCustomerList()
        salesRepViewModel.getCustomerList()

        getCustomerListObservers()
    }

    private fun getCustomerListObservers()
    {
        salesRepViewModel.getCustomerListResponse().observe(lifecycleOwner) {
            if (it.code == 200) {
                if (it.customerList.isNotEmpty()) {
                    onCustomersListFetched.invoke(it.customerList)
                } else {
                    UtilFunctions.showToast(context, "No Customers Found")
                }
            }
        }
    }

    fun getProductsList()
    {
        salesRepViewModel.resetProductsList()
        salesRepViewModel.getProductsList()

        getProductsListObservers()
    }

    private fun getProductsListObservers()
    {
        salesRepViewModel.getProductsListResponse().observe(lifecycleOwner) {
            if (it.code == 200) {
                if (it.productStockList.isNotEmpty()) {
                    onProductsListFetched.invoke(it.productStockList)
                } else {
                    UtilFunctions.showToast(context, "No Products Found")
                }
            }
        }
    }

    fun getOutstandingList(cusId: Int, repId: Int)
    {
        salesRepViewModel.resetGetOutstandingList()
        salesRepViewModel.getOutstandingList(cusId, repId)

        getOutstandingListObservers()
    }

    private fun getOutstandingListObservers()
    {
        salesRepViewModel.getOutstandingListResponse().observe(lifecycleOwner) {
            if (it.code == 200) {
                if (it.outstandingList.isNotEmpty()) {
                    onOutstandingListFetched.invoke(it.outstandingList)
                } else {
                    UtilFunctions.showToast(context, "No Outstandings Found")
                }
            }
        }
    }
}