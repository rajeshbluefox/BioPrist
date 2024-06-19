package bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass

import com.google.gson.annotations.SerializedName



data class AreaItem(

    @field:SerializedName("TWMSM_REPM_ID")
    val repId: String? = null,

    @field:SerializedName("RepName")
    val repName: String? = null,

    @field:SerializedName("TINVM_ARM_ID")
    val areaId: String? = null,

    @field:SerializedName("ARM_AreaName")
    val areaName: String? = null,

    @field:SerializedName("MEXT_Value")
    val MEXT_Value: String? = null,


)