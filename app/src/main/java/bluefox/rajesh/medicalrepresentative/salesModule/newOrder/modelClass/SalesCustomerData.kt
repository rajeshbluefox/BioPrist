package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName
data class SalesCustomerData(
    @SerializedName("CustomerId")
    var customerId: Int? = null,
    @SerializedName("CustomerName")
    var customerName: String? = null,
)