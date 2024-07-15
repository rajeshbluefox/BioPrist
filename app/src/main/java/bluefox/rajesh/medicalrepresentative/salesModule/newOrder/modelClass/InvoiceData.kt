package bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass

import com.google.gson.annotations.SerializedName

data class InvoiceData(
    @SerializedName("TWMSM_CUM_ID")
    var twmsmCumId: String? = "1",
    @SerializedName("InvoiceDate")
    var invoiceDate: String? = "2024-07-15 00:00:00",
    @SerializedName("InvoiceNo")
    var invoiceNumber: String? = "1",
    @SerializedName("InvoiceTaxable")
    var invoiceTaxable: String? = "350.000",
    @SerializedName("InvoiceAmount")
    var invoiceAmount: String? = "403.000",
    @SerializedName("BalanceAmount")
    var balanceAmount: String? = "403.000",
    @SerializedName("Company")
    var company: String? = "",
    @SerializedName("Days")
    var days: Int? = 0,
    @SerializedName("TransId")
    var transId: String? = "1",
    @SerializedName("TransType")
    var transType: String? = "SAL"
)