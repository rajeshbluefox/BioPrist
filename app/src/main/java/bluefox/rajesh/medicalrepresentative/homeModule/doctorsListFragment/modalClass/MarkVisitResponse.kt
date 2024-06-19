package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName


data class MarkVisitResponse (
    @SerializedName("code")
    val code: Int = 195,
    @SerializedName("message")
    val message: String = "",
)