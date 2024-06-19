package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("CustomerId")
    var DoctorId: Int = -1,
    @SerializedName("GiftGiven")
    var giftGiven: Int = 0,
    @SerializedName("CustomerName")
    var DoctorName: String = "",
    @SerializedName("CustomerCode")
    var CustomerCode: Int = -1,
    @SerializedName("BuildingName")
    var BuildingName: String = "",
    @SerializedName("Location")
    var Location: String = "",
    @SerializedName("ContactNumber1")
    var ContactNumber1: String = ""
)