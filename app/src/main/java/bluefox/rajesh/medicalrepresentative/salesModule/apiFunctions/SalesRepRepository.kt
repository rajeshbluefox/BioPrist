package bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions

import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.getProductsResponse
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.GetCustomersResponse
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.GetProductsResponse
import bluefox.rajesh.medicalrepresentative.salesModule.outstanding.modalClass.GetOutstandingResponse
import bluefox.rajesh.medicalrepresentative.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SalesRepRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var getCustomersResponse = GetCustomersResponse()

    suspend fun getCustomersList(): GetCustomersResponse {
        try {
            withContext(Dispatchers.IO) {
                getCustomersResponse = apiHelper.getCustomers()
            }
        } catch (ex: Exception) {
        }
        return getCustomersResponse
    }

    private var getProductsResponse = GetProductsResponse()

    suspend fun getProductsList(): GetProductsResponse {
        try {
            withContext(Dispatchers.IO) {
                getProductsResponse = apiHelper.getProductsSalesRep()
            }
        } catch (_: Exception) {
        }
        return getProductsResponse
    }

    private var getOutstandingResponse = GetOutstandingResponse()

    suspend fun getOutstadingList(custId: Int, repId: Int): GetOutstandingResponse {
        try {
            withContext(Dispatchers.IO) {
                getOutstandingResponse = apiHelper.getOutstandingList(custId, repId)
            }
        } catch (_: Exception) {
        }
        return getOutstandingResponse
    }

}