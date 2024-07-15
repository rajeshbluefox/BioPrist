package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName


data class GetCustomersResponse (
    @SerializedName("code")
    val code: Int = 195,
    @SerializedName("CustomerList")
    val customerList: ArrayList<SalesCustomerData> =ArrayList()
)