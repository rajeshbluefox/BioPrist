package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName


data class MedicineData(
    @SerializedName("CustomerId")
    var customerId: Int = 0,
    @SerializedName("MedicineId")
    var medicineId: Int = 0,
    @SerializedName("MedicineName")
    var medicineName: String? = "Dolo",
    @SerializedName("Rate")
    var rate: String? = "100",
    @SerializedName("MRP")
    var mrp: String? = "150",
    @SerializedName("Stock")
    var stock: String? = "50",
    @SerializedName("Quantity")
    var quantity: String? = "0",
    @SerializedName("QuantityType")
    var quantityType: String = "Qua"
)