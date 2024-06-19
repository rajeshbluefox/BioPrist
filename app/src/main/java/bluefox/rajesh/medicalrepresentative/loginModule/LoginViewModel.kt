package bluefox.rajesh.medicalrepresentative.loginModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.GetRepresentativeResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.LoginResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.UpdateLoginStatusResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private var loginResponse = MutableLiveData<LoginResponse>()

    fun postUserNameandPassword(userName: String,password: String) {
        viewModelScope.launch {
            loginResponse.postValue(loginRepository.postUserNameandPassword(userName,password))
        }
    }

    fun getLoginResponse() : LiveData<LoginResponse>
    {
        return loginResponse
    }


    private var getRepresentativeResponse = MutableLiveData<GetRepresentativeResponse>()

    fun getRepresentativeList(repId: Int)
    {
        viewModelScope.launch {
            getRepresentativeResponse.postValue(loginRepository.getRepresentativeList(repId))
        }
    }

    fun getRepresentativeResponse() : LiveData<GetRepresentativeResponse>
    {
        return getRepresentativeResponse
    }

    fun reset()
    {
        getRepresentativeResponse = MutableLiveData<GetRepresentativeResponse>()
    }


    private var updateLoginActivity = MutableLiveData<UpdateLoginStatusResponse>()

    fun updateLoginStatus(empId: Int, status: Int)
    {
        viewModelScope.launch {
            updateLoginActivity.postValue(loginRepository.updateLoginStatus(empId, status))
        }
    }

    fun updateLoginStatusResponse() : LiveData<UpdateLoginStatusResponse>
    {
        return updateLoginActivity
    }

    fun resetUpdateLoginStatus()
    {
        updateLoginActivity = MutableLiveData<UpdateLoginStatusResponse>()
    }
}