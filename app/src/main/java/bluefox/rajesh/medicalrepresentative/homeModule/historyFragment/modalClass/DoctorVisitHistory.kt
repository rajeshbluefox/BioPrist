package bluefox.rajesh.medicalrepresentative.homeModule.historyFragment.modalClass

import com.google.gson.annotations.SerializedName


data class DoctorVisitHistory(
    @SerializedName("DoctorId")
    var DoctorId: Int = -1,
    @SerializedName("DoctorName")
    var DoctorName: String = "",
    @SerializedName("Department")
    var Department: Int = -1,
    @SerializedName("HospitalId")
    var HospitalId: Int = -1,
    @SerializedName("Qualification")
    var Qualification: String = "",
    @SerializedName("RegNo")
    var RegNo: String = "",
    @SerializedName("IsLeave")
    var IsLeave: Int = -1,
    @SerializedName("IsSelected")
    var isSelected: Boolean = false,
    @SerializedName("VisitedDate")
    var dateOfVisit: String = "31-08-2023",
)