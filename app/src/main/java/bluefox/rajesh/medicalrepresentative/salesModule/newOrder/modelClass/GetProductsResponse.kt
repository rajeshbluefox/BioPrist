package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName


data class GetProductsResponse (
    @SerializedName("code")
    val code: Int = 195,
    @SerializedName("ProductStockList")
    val productStockList: ArrayList<ProductStockData> =ArrayList()
)