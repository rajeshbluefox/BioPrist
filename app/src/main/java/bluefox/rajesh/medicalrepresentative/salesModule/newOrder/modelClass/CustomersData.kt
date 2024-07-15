package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName

data class CustomersData(
    @SerializedName("CustomerId")
    var customerId: Int = -1,
    @SerializedName("CustomerCode")
    var customerCode: String = "",
    @SerializedName("CustomerName")
    var customerName: String = ""
)