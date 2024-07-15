package bluefox.rajesh.medicalrepresentative.salesModule.outstanding.modalClass

import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.InvoiceData
import com.google.gson.annotations.SerializedName

data class GetOutstandingResponse (
    @SerializedName("code")
    val code: Int = 195,
    @SerializedName("OutstandingList")
    val outstandingList: ArrayList<InvoiceData> =ArrayList()
)