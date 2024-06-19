package bluefox.rajesh.medicalrepresentative.homeModule.apiFunctions

import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.AreasListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.DoctorsListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.GetVisitHistoryResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitData
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateDoctorResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateStatusResponse
import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.getProductsResponse
import bluefox.rajesh.medicalrepresentative.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var getproductsresponse = getProductsResponse()

    suspend fun getProductList(): getProductsResponse {
        try {
            withContext(Dispatchers.IO) {
                getproductsresponse = apiHelper.getProductsList()
            }
        } catch (ex: Exception) {
        }
        return getproductsresponse
    }

    private var doctorsListResponse = DoctorsListResponse()

    suspend fun getDoctorsList(repId: Int,areaId: Int): DoctorsListResponse {
        try {
            withContext(Dispatchers.IO) {
                doctorsListResponse = apiHelper.gDoctorsList(repId,areaId)
            }
        } catch (ex: Exception) {
        }
        return doctorsListResponse
    }

    private var updateDoctorResponse = UpdateDoctorResponse()

    suspend fun updateDoctorsDetails(doctor: Doctor): UpdateDoctorResponse {
        try {
            withContext(Dispatchers.IO) {
                updateDoctorResponse = apiHelper.pUpdateDoctor(doctor)
            }
        } catch (ex: Exception) {
        }
        return updateDoctorResponse
    }

    private var markVisitResponse = MarkVisitResponse()

    suspend fun markVisit(markVisitData: MarkVisitData): MarkVisitResponse {
        try {
            withContext(Dispatchers.IO) {
                markVisitResponse = apiHelper.pMarkVisit(markVisitData)
            }
        } catch (ex: Exception) {
        }
        return markVisitResponse
    }

    private var updateStatusResponse = UpdateStatusResponse()

    suspend fun updateVisitStatus(statusId: Int,statusCode: Int,description: String): UpdateStatusResponse {
        try {
            withContext(Dispatchers.IO) {
                updateStatusResponse = apiHelper.updateVisitStatus(statusId, statusCode,description)
            }
        } catch (ex: Exception) {
        }
        return updateStatusResponse
    }

    private var getVisitHistoryResponse = GetVisitHistoryResponse()

    suspend fun getVisitHistory(statusId: Int): GetVisitHistoryResponse {
        try {
            withContext(Dispatchers.IO) {
                getVisitHistoryResponse = apiHelper.getVisitHistory(statusId)
            }
        } catch (ex: Exception) {
        }
        return getVisitHistoryResponse
    }

    private var areasListResponse = AreasListResponse()

    suspend fun getAreasList(statusId: Int): AreasListResponse {
        try {
            withContext(Dispatchers.IO) {
                areasListResponse = apiHelper.getAreasList(statusId)
            }
        } catch (ex: Exception) {
        }
        return areasListResponse
    }



}