package bluefox.rajesh.medicalrepresentative.homeModule.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.AreasListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.Doctor
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.DoctorsListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.GetVisitHistoryResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitData
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateDoctorResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateStatusResponse
import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.getProductsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val getproductresponse = MutableLiveData<getProductsResponse>()

    private var doctorsListResponse = MutableLiveData<DoctorsListResponse>()

    private var areasListResponse = MutableLiveData<AreasListResponse>()

    fun postProductsList(repId: Int,areaId: Int) {
        viewModelScope.launch {
//            getproductresponse.postValue(homeRepository.getProductList())

            doctorsListResponse.postValue(homeRepository.getDoctorsList(repId,areaId))
        }

    }

    private var updateDoctorResponse = MutableLiveData<UpdateDoctorResponse>()

    fun postUpdateDoctorsDetails(doctor: Doctor)
    {
        viewModelScope.launch {
            updateDoctorResponse.postValue(homeRepository.updateDoctorsDetails(doctor))
        }
    }

    private var markVisitResponse = MutableLiveData<MarkVisitResponse>()

    fun markVisit(markVisitData: MarkVisitData)
    {
        viewModelScope.launch {
            markVisitResponse.postValue(homeRepository.markVisit(markVisitData))
        }
    }

    private var updateStatusResponse = MutableLiveData<UpdateStatusResponse>()

    fun updateVisitStatus(statusId: Int,statusCode: Int,description: String)
    {
        viewModelScope.launch {
            updateStatusResponse.postValue(homeRepository.updateVisitStatus(statusId, statusCode,description))
        }
    }

    private var getVisitHistoryResponse = MutableLiveData<GetVisitHistoryResponse>()

    fun getVisitHistory(repId: Int)
    {
        viewModelScope.launch {
            getVisitHistoryResponse.postValue(homeRepository.getVisitHistory(repId))
        }
    }

    fun getAreasList(repId : Int)
    {
        viewModelScope.launch {
            areasListResponse.postValue(homeRepository.getAreasList(repId))
        }
    }

    fun getProductsList(): LiveData<getProductsResponse>
    {
        return  getproductresponse
    }

    fun getDoctorsList(): LiveData<DoctorsListResponse>
    {
        return doctorsListResponse
    }

    fun getUpdateDoctorResponse(): LiveData<UpdateDoctorResponse>
    {
        return updateDoctorResponse
    }

    fun markVisitResponse(): LiveData<MarkVisitResponse>
    {
        return markVisitResponse
    }


    fun getUpdatedVisitResponse(): LiveData<UpdateStatusResponse>
    {
        return updateStatusResponse
    }


    fun getVisitHistoryResponse(): LiveData<GetVisitHistoryResponse>
    {
        return getVisitHistoryResponse
    }

    fun getAreasListResponse() : LiveData<AreasListResponse>
    {
        return areasListResponse
    }

    fun reset()
    {
        doctorsListResponse = MutableLiveData<DoctorsListResponse>()
        updateDoctorResponse = MutableLiveData<UpdateDoctorResponse>()
        markVisitResponse = MutableLiveData<MarkVisitResponse>()
        updateStatusResponse = MutableLiveData<UpdateStatusResponse>()
        getVisitHistoryResponse = MutableLiveData<GetVisitHistoryResponse>()

        areasListResponse = MutableLiveData<AreasListResponse>()
    }

}