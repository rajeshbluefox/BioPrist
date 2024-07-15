package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName

data class ProductStockData(
    @SerializedName("ProductId")
    var productId: Int = -1,
    @SerializedName("ProductCode")
    var productCode: String = "",
    @SerializedName("ProductName")
    var productName: String = "",
    @SerializedName("ProductGroupId")
    var productGroupId: Int = -1,
    @SerializedName("RatePurchase")
    var ratePurchase: String = "",
    @SerializedName("RateSales")
    var rateSales: String = "",
    @SerializedName("MRP")
    var mrp: String = "",
    @SerializedName("StockBookId")
    var stockBookId: Int = -1,
    @SerializedName("ProductDetailId")
    var productDetailId: Int = -1,
    @SerializedName("EntryDate")
    var entryDate: String = "",
    @SerializedName("RefNo")
    var refNo: String = "",
    @SerializedName("ItemPerPack")
    var itemPerPack: Double = 0.0,
    @SerializedName("TotItemsIn")
    var totItemsIn: String = "",
    @SerializedName("TotItemsOut")
    var totItemsOut: Double = 0.0,
    @SerializedName("TotItemsFree")
    var totItemsFree: Double = 0.0,
    @SerializedName("Quantity")
    var quantity: String? = "0",
    @SerializedName("FreeQuantity")
    var freeQuantity: String? = "0",
    @SerializedName("QuantityType")
    var quantityType: String = "Qua",
    @SerializedName("CustomerId")
    var customerId: Int = 0,
    @SerializedName("CustomerName")
    var customerName: String = "",
)
