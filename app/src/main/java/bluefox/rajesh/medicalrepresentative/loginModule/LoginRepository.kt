package bluefox.rajesh.medicalrepresentative.loginModule

import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.GetRepresentativeResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.LoginResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.UpdateLoginStatusResponse
import bluefox.rajesh.medicalrepresentative.zAPIEndPoints.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    private var loginResponse = LoginResponse()

    suspend fun postUserNameandPassword(userName: String,password: String): LoginResponse {
        try {
            withContext(Dispatchers.IO) {
                loginResponse = apiHelper.postUserNameandPassword(userName,password)
            }
        } catch (_: Exception) { }
        return loginResponse
    }

    private var getRepresentativeResponse = GetRepresentativeResponse()

    suspend fun getRepresentativeList(repId: Int): GetRepresentativeResponse {
        try {
            withContext(Dispatchers.IO) {
                getRepresentativeResponse = apiHelper.getRepresentativesList(repId)
            }
        } catch (_: Exception) { }
        return getRepresentativeResponse
    }

    private var updateLoginStatusResponse = UpdateLoginStatusResponse()

    suspend fun updateLoginStatus(empId: Int,status: Int): UpdateLoginStatusResponse {
        try {
            withContext(Dispatchers.IO) {
                updateLoginStatusResponse = apiHelper.updateLoginStatus(empId, status  )
            }
        } catch (_: Exception) { }
        return updateLoginStatusResponse
    }
}