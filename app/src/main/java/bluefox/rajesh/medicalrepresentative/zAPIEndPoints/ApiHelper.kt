package bluefox.rajesh.medicalrepresentative.zAPIEndPoints

import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.AreasListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.DoctorsListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.GetVisitHistoryResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitData
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateDoctorResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateStatusResponse
import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.getProductsResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.GetRepresentativeResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.LoginResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.UpdateLoginStatusResponse

interface ApiHelper {

    suspend fun postUserNameandPassword(userName: String, password: String): LoginResponse

    suspend fun updateLoginStatus(empId: Int, status: Int): UpdateLoginStatusResponse

    suspend fun getProductsList(): getProductsResponse

    suspend fun getRepresentativesList(repId: Int): GetRepresentativeResponse

    suspend fun gDoctorsList(repId: Int,areaId: Int): DoctorsListResponse

    suspend fun pUpdateDoctor(doctor: Doctor): UpdateDoctorResponse

    suspend fun pMarkVisit(markVisitData: MarkVisitData): MarkVisitResponse

    suspend fun updateVisitStatus(statusId: Int,statusCode: Int,description: String): UpdateStatusResponse

    suspend fun getVisitHistory(repId: Int): GetVisitHistoryResponse

    suspend fun getAreasList(repId: Int): AreasListResponse


}