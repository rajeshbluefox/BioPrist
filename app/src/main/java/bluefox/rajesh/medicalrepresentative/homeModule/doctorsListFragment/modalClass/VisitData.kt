package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName


data class VisitData(
    @SerializedName("TMWSWD_RCV_ID")
    var RowId: Int = -1,
    @SerializedName("TWMSM_CUM_ID")
    var CustomerId: Int = -1,
    @SerializedName("CustomerName")
    var CustomerName: String = "",
    @SerializedName("RCV_CreateddDate")
    var VisitedDate: String = "",
)