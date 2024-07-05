package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName

data class InvoiceData (
    @SerializedName("InvoiceDate")
    var invoiceDate: String? = "12-10-2024",
    @SerializedName("InvoiceNumber")
    var invoiceNumber: String? = "1234",
    @SerializedName("Amount")
    var amount: String? = "100",
    @SerializedName("Balance")
    var balance: String? = "150",
    @SerializedName("Days")
    var days: String? = "360",

)