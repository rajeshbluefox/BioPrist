package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName

data class DoctorsListResponse (
    @SerializedName("code")
    val code: Int = 195,
    @SerializedName("CustomersList")
    val DoctorsList: ArrayList<Doctor> =ArrayList()
)