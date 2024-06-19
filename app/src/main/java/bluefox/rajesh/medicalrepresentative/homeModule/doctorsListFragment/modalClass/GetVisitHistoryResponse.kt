package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName


data class GetVisitHistoryResponse (
    @SerializedName("code")
    val code: Int = 195,
    @SerializedName("Data")
    val visitHistoryList: ArrayList<VisitData> =ArrayList()
)