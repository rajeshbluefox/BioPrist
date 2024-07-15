package bluefox.rajesh.medicalrepresentative.zAPIEndPoints

import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.AreasListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.DoctorsListResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.GetVisitHistoryResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.MarkVisitResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateDoctorResponse
import bluefox.rajesh.medicalrepresentative.homeModule.doctorsListFragment.modalClass.UpdateStatusResponse
import bluefox.rajesh.medicalrepresentative.homeModule.modalClass.getProductsResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.GetRepresentativeResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.LoginResponse
import bluefox.rajesh.medicalrepresentative.loginModule.modalClass.UpdateLoginStatusResponse
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.GetCustomersResponse
import bluefox.rajesh.medicalrepresentative.salesModule.newOrder.modelClass.GetProductsResponse
import bluefox.rajesh.medicalrepresentative.salesModule.outstanding.modalClass.GetOutstandingResponse
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("appLogin.php")
    suspend fun postUserNameandPassword(
        @Field("MobileNo") phoneNo: String,
        @Field("Password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("updateLoginStatus.php")
    suspend fun updateLoginStatus(
        @Field("TM_UM_ID") empId: Int,
        @Field("UM_ApproveStatus") status: Int,
    ): UpdateLoginStatusResponse

    @GET("getCustomers.php")
    suspend fun getProductList(): getProductsResponse


    @FormUrlEncoded
    @POST("getMedRep.php")
    suspend fun getRepresentativeList(
        @Field("RepId") repId: Int,
    ): GetRepresentativeResponse


    @FormUrlEncoded
    @POST("getCustomers.php")
    suspend fun getDoctorList(
        @Field("RepId") RepId: Int,
        @Field("AreaId") areaId: Int,
    ): DoctorsListResponse

    @FormUrlEncoded
    @POST("REPM_ID.php")
    suspend fun getAreasList(
        @Field("RepmId") RepId: Int,
    ): AreasListResponse


    @FormUrlEncoded
    @POST("updateDoctor.php")
    suspend fun updateDoctorDetails(
        @Field("TWMSM_CUM_ID") doctorId: Int,
        @Field("CustomerName") doctorName: String,
        @Field("BuildingName") buildingName: String,
        @Field("Location") location: String,
        @Field("ContactNumber1") contactNumber: String,
        ): UpdateDoctorResponse


    @FormUrlEncoded
    @POST("insertvisit.php")
    suspend fun markVisit(
        @Field("TWMSM_REPM_ID") representativeId: Int,
        @Field("TWMSM_CUM_ID") customerId: Int,
        @Field("RCV_Latitude") latitude: String,
        @Field("RCV_Longitude") longitude: String,
        @Field("GiftGiven") giftGiven: Int,
    ): MarkVisitResponse



    @FormUrlEncoded
    @POST("updatestatus.php")
    suspend fun updateVisitStatus(
        @Field("TMWSWD_RCV_ID") statusId: Int,
        @Field("NewRCV_RowStatus") statusCode: Int,
        @Field("RCV_Description") description: String,
    ): UpdateStatusResponse

    @FormUrlEncoded
    @POST("getVisitHistory.php")
    suspend fun getVisitHistory(
        @Field("RepmId") repIs: Int,
    ): GetVisitHistoryResponse

    //Sales Rep APIs

    @GET("getCustomers_SalesRep.php")
    suspend fun getCustomersList(): GetCustomersResponse

    @GET("getProducts_SalesRep.php")
    suspend fun getProductsList(): GetProductsResponse


    @FormUrlEncoded
    @POST("getOutstanding_byCustId.php")
    suspend fun getOutstadingList(
        @Field("TWMSM_CUM_ID") custId: Int,
        @Field("TWMSM_REPM_ID") repId: Int,
    ): GetOutstandingResponse



}