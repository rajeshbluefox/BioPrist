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
import javax.inject.Inject


class ApiHelperImplementation @Inject constructor(private val apiService: ApiInterface) :
    ApiHelper {

    override suspend fun postUserNameandPassword(
        userName: String,
        password: String
    ): LoginResponse {
        return apiService.postUserNameandPassword(userName, password)
    }

    override suspend fun updateLoginStatus(
        empId: Int,
        status: Int
    ): UpdateLoginStatusResponse {
        return apiService.updateLoginStatus(empId, status)
    }

    override suspend fun getProductsList(): getProductsResponse {
        return apiService.getProductList()
    }

    override suspend fun getRepresentativesList(repId: Int): GetRepresentativeResponse {
        return apiService.getRepresentativeList(repId)
    }

    override suspend fun gDoctorsList(repId: Int, areaId: Int): DoctorsListResponse {
        return apiService.getDoctorList(repId, areaId)
    }

    override suspend fun pUpdateDoctor(doctor: Doctor): UpdateDoctorResponse {
        return apiService.updateDoctorDetails(
            doctor.DoctorId,
            doctor.DoctorName,
            doctor.BuildingName,
            doctor.Location,
            doctor.ContactNumber1
        )
    }

    override suspend fun pMarkVisit(markVisitData: MarkVisitData): MarkVisitResponse {
        return apiService.markVisit(
            markVisitData.RepId,
            markVisitData.CustomerId,
            markVisitData.Latitude,
            markVisitData.Longitude,
            markVisitData.giftGiven
        )
    }

    override suspend fun updateVisitStatus(
        statusId: Int,
        statusCode: Int,
        description: String
    ): UpdateStatusResponse {
        return apiService.updateVisitStatus(
            statusId, statusCode, description
        )
    }

    override suspend fun getVisitHistory(repId: Int): GetVisitHistoryResponse {
        return apiService.getVisitHistory(repId)
    }

    override suspend fun getAreasList(repId: Int): AreasListResponse {
        return apiService.getAreasList(repId)
    }


}