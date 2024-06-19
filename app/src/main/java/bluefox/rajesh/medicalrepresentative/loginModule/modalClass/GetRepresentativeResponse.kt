package bluefox.rajesh.medicalrepresentative.loginModule.modalClass

import com.google.gson.annotations.SerializedName


data class GetRepresentativeResponse (
    @SerializedName("code")
    val code: Int = -1,
    @SerializedName("RepresentativesList")
    val representativesList :  ArrayList<RepresentativeData> = ArrayList()
)