package bluefox.rajesh.medicalrepresentative.homeModule.modalClass

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("ProductId")
    var ProductId: Int? = null,
    @SerializedName("ProductCode")
    var ProductCode: Int? = null,
    @SerializedName("ProductName")
    var ProductName: String ,
    @SerializedName("MRP")
    var MRP: Int? = null,
    @SerializedName("TotalMRP")
    var TotalMRP: Int? = null,
    @SerializedName("Photo")
    var Photo: String? = null,
    @SerializedName("PhotoName")
    var PhotoName: String? = null,
    @SerializedName("ProductCategoryCode")
    var ProductCategoryCode: Int? ,
    @SerializedName("ProductCategoryName")
    var ProductCategoryName: String? ,
    @SerializedName("Stock")
    var Stock: Int ,
    @SerializedName("IsItemAdded")
    var IsItemAdded: Boolean? = false,
    @SerializedName("Quantity")
    var Quantity: Int = 0,
    @SerializedName("Weight")
    var Weight: Float =0.0f,
    @SerializedName("SubTotal")
    var SubTotal: Float =0.0f,
    @SerializedName("Message")
    var Message: String = "No Message",
    @SerializedName("CakeModel")
    var CakeModel: Int = 1,
    @SerializedName("AdditionalCharge")
    var AdditionalCharge: Int = 0,

)