package bluefox.rajesh.medicalrepresentative.loginModule.modalClass

import com.google.gson.annotations.SerializedName



data class RepresentativeData(
    @SerializedName("RepId")
    var RepId: Int? = null,
    @SerializedName("RepCode")
    var RepCode: Int? = null,
    @SerializedName("RepName")
    var RepName: String ,
)