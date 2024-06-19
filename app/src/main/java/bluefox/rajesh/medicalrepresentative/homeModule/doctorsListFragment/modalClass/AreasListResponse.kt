package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName



data class AreasListResponse (
    @SerializedName("status")
    val code: Int = 195,
    @SerializedName("data")
    val areaList: ArrayList<AreaItem> =ArrayList()
)