package bluefox.rajesh.medicalrepresentative.homeModule.modalClass

import com.google.gson.annotations.SerializedName

data class getProductsResponse (
    @SerializedName("code")
    val code: Int = -1,
    @SerializedName("DoctorsList")
    val clusters :  ArrayList<Product> = ArrayList()
)