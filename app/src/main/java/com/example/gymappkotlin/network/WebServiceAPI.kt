package com.example.gymappkotlin.network

import com.example.gymappkotlin.model.*
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


/**
 **The web API interface**
 * Please define all your web API extensions here
 */
interface WebServiceAPI {

    @POST("api/user/login")
    fun verifyLogin(@Body field: JsonObject): Call<LoginResponse>


    @GET("api/Dashboard/GetCountsForDashboard/")
    fun getDashBoard(): Call<DashbordResponse>

    @GET("api/ServiceRequest/SearchList?")
    fun getServiceData(
        @Query("search") search: String?,
        @Query("status") status: String?,
        @Query("supporttype") supporttype: String?,
        @Query("page") page: Int?,
        @Query("pageType") pageType: String?
    ): Call<ServiceResponse>



    @GET("api/ServiceRequest/SearchList?search")
    fun getSuggestionData(
        @Query("status") status: String?,
        @Query("supporttype") supporttype: String?,
        @Query("page") page: String?,
        @Query("pageType") pageType: String?
    ): Call<ServiceResponse>

    @GET("api/Common/GetTypes")
    fun getSupportType(): Call<TypeResponse>

    @GET("api/user/SearchMember?")
    fun getSearchManger(
        @Query("searchcriteria") status: String?
    ): Call<SearchManager>

    @Multipart
    @POST("api/ServiceRequest/PostDataV2")
    fun AddorEditService(
        @Part ("Id") Id: RequestBody,
        @Part("ServiceSupportTypeId") ServiceSupportTypeId:RequestBody,
        @Part("StatusId") StatusId:RequestBody,
        @Part("Description") Description:RequestBody,
        @Part("memberId") memberId:RequestBody,
        @Part("unitId") unitId:RequestBody,
        @Part("PageType") PageType:RequestBody,
        @Part("isServiceRequest") isServiceRequest:RequestBody,
        @Part  body : MultipartBody.Part)
        : Call<ResponseModel>

    @Multipart
    @POST("api/ServiceRequest/PostDataV2")
    fun EditService(
        @Part ("Id") Id: RequestBody,
        @Part("ServiceSupportTypeId") ServiceSupportTypeId:RequestBody,
        @Part("StatusId") StatusId:RequestBody,
        @Part("Description") Description:RequestBody,
        @Part("memberId") memberId:RequestBody,
        @Part("unitId") unitId:RequestBody,
        @Part("PageType") PageType:RequestBody,
        @Part("isServiceRequest") isServiceRequest:RequestBody,
        @Part("Response") response:RequestBody,
        @Part("ResponseBy") responseBy:RequestBody,
        @Part("AttendedBy") attendby:RequestBody,
        @Part("ServiceType") serviceType:RequestBody,
        @Part("ServiceAmount") serviceAmount:RequestBody,
        @Part  body : MultipartBody.Part)

       : Call<ResponseModel>

    @GET("api/ServiceRequest/GetRequestLog?")
      fun getRequestLog(
          @Query("id") search: String?
      ): Call<RequestLog>

    @GET("api/Dashboard/GetUnApprovedIdenityList")
    fun getUnapproveIdentityList(): Call<unApproveIdentity>
}
