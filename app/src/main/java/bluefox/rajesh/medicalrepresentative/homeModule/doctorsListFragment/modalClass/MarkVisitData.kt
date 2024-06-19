package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName

data class MarkVisitData(
    @SerializedName("RepId")
    var RepId: Int = -1,
    @SerializedName("CustomerId")
    var CustomerId: Int = -1,
    @SerializedName("GiftGiven")
    var giftGiven: Int = 0,
    @SerializedName("Latitude")
    var Latitude: String = "",
    @SerializedName("Longitude")
    var Longitude: String = "",
    @SerializedName("ContactNumber")
    var ContactNumber: String = "",
)