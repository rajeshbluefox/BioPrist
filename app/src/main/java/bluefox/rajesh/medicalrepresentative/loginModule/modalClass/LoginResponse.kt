package bluefox.rajesh.medicalrepresentative.loginModule.modalClass

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    val code: Int = 195,
    @SerializedName("TM_UM_ID")
    val userId: Int = -1,
    @SerializedName("EmployeeId")
    val employeeId: Int = -1
//    @SerializedName("message")
//    val message: String = "",
//    @SerializedName("username")
//    val username: String = "",
//    @SerializedName("ContactNumber")
//    val phoneNo: String = "",
//    @SerializedName("Photo")
//    val PhotoName: String = "",
//    @SerializedName("userId")
//    val userId: String = "",
//    @SerializedName("status")
//    val status: String = ""

)