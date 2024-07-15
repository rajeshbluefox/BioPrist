package bluefox.rajesh.medicalrepresentative.salesModule.apiFunctions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.getProductsResponse
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.GetCustomersResponse
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.GetProductsResponse
import bluefox.rajesh.medicalrepresentative.salesModule.outstanding.modalClass.GetOutstandingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SalesRepViewModel @Inject constructor(
    private val salesRepRepository: SalesRepRepository
) : ViewModel() {

    private var getCustomersResponse = MutableLiveData<GetCustomersResponse>()

    fun getCustomerList()
    {
        viewModelScope.launch {
            getCustomersResponse.postValue(salesRepRepository.getCustomersList())
        }
    }

    fun resetCustomerList()
    {
        getCustomersResponse = MutableLiveData<GetCustomersResponse>()
    }


    fun getCustomerListResponse(): LiveData<GetCustomersResponse>
    {
        return  getCustomersResponse
    }

    private var getProductsResponse = MutableLiveData<GetProductsResponse>()

    fun getProductsList()
    {
        viewModelScope.launch {
            getProductsResponse.postValue(salesRepRepository.getProductsList())
        }
    }

    fun resetProductsList()
    {
        getProductsResponse = MutableLiveData<GetProductsResponse>()
    }


    fun getProductsListResponse(): LiveData<GetProductsResponse>
    {
        return  getProductsResponse
    }

    private var getOutstandingResponse = MutableLiveData<GetOutstandingResponse>()

    fun getOutstandingList(custId: Int, repId: Int)
    {
        viewModelScope.launch {
            getOutstandingResponse.postValue(salesRepRepository.getOutstadingList(custId, repId))
        }
    }

    fun resetGetOutstandingList()
    {
        getOutstandingResponse = MutableLiveData<GetOutstandingResponse>()
    }


    fun getOutstandingListResponse(): LiveData<GetOutstandingResponse>
    {
        return  getOutstandingResponse
    }


}