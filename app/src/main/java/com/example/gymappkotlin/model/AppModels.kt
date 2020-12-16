package com.example.gymappkotlin.model

import java.io.Serializable


open class ResponseModel(
    var status: Int = 0,
    var message: String = ""
)

data class DataWrapper<T>(
    var data: T? = null,
    var message: String? = null,
    var status: Int = 0,
)

data class LoginResponse(
    val result: User
): ResponseModel()


data class AddService(
    val result: String
): ResponseModel()


data class User(
    val authToken: String,
    val companyName: String,
    val emailID: String,
    val firstName: String,
    val id: Int,
    val mobileNo: String,
    val surName: String,
    val userName: String,
    val userToken: String
): ResponseModel()


data class DashbordResponse (
    val result: DashbordData
): ResponseModel()

data class DashbordData (
    var identityIn: String? = null,
    var identityOut: String? = null,
    var visitorIn: String? = null,
    var visitorOut: String? = null,
    var pendingOut: String? = null,
    var vehicleIn: String? = null,
    var vehicleOut: String? = null,
    var inProgressSuggetionRequest: String? = null,
    var pendingSuggetionRequest: String? = null,
    var reopenSuggetionRequest: String? = null,
    var pendingServiceRequest: String? = null,
    var inProgressServiceRequest: String? = null,
    var reopenServiceRequest: String? = null,
    var totalUnit: String? = null,
    var unallocatedUnits: String? = null,
    var totalOwnerOccupied: String? = null,
    var totalLease: String? = null,
    var totalDormat: String? = null,
    var expiringIdentity: String? = null,
    var totalReceiptAmount: String? = null,
    var totalReceiptCount: String? = null,
    var totalVoucerAmount: String? = null,
    var totalVoucerCount: String? = null,
    var totalPaymentAmount: String? = null,
    var totalPaymentCount: String? = null,
    var maintanceMessaage: String? = null,
    var totalTodayUnit: String? = null,
    var totalTodayIdentity: String? = null,
    var todayEvent: List<Any>? = null,
    var upcomingEvent: List<Any>? = null,
    var todayFacility: List<Any>? = null,
    var upcomingFacility: List<Any>? = null,
    var todayClassifiedPosting: List<Any>? = null,
    var pendingApproval: String? = null,
    var notificationCount: String? = null
    ):Serializable, ResponseModel()

 data class ServiceResponse (
        val result: ArrayList<ServiceModel>
    ): ResponseModel()

data class ServiceModel (
    var rowNum: String? = null,
    var id: String? = null,
    var memberId: String? = null,
    var serviceSupportTypeId: String? = null,
    var statusId: String? = null,
    var description: String? = null,
    var unitName: String? = null,
    var unitId: String? = null,
    var identityName: String? = null,
    var mobileNo: String? = null,
    var priorityVal: String? = null,
    var createdOn: String? = null,
    var updatedOn: String? = null,
    var typeName: String? = null,
    var statusDesc: String? = null,
    var pageType: String? = null,
    var serviceCharge: String? = null,
    var rating: String? = null,
    var total: String? = null,
    var adminDocumentPath: String? = null,
    var memberDocumentPath: String? = null,
    var response: String? = null,
    var responseBy: String? = null,
    var servicePersonName: String? = null,
    var serviceAmount: String? = null,
    var serviceType: String? = null,
    var communicationCount: String? = null
):Serializable, ResponseModel()


 data class TypeResponse (
        val result: ArrayList<Result>
    ): ResponseModel()

    data class Result (
        var supportTypes: List<SupportType>? = null,
        var statusTypes: List<StatusType>? = null
    ): ResponseModel()

    data class StatusType (
        var id: Int? = null,
        var statusDesc: String? = null
    ): ResponseModel()

    data class SupportType (
        var id: Int? = null,
        var typeName: String? = null,
        var isSuggestion: Boolean? = null
    ): ResponseModel()


    data class SearchManager (
        val result: ArrayList<SearchManagerList>
    ): ResponseModel()


    data class SearchManagerList (
        var id: Int? = null,
        var name: String? = null,
        var unitId: Int? = null
    ): ResponseModel()


    data class RequestLog (
        val result: ArrayList<RequestLogList>

    ): ResponseModel()

    data class RequestLogList (
        var id: Int? = null,
        var statusId: String? = null,
        var description: String? = null,
        var userName: String? = null,
        var createdOn: String? = null,
        var isComment: Boolean
    ): ResponseModel()

    data class unApproveIdentity (
        val result: ArrayList<UnApproveIdentityList>

    ): ResponseModel()

    data class UnApproveIdentityList (
        var id: Int? = null,
        var mobile: String? = null,
        var gender: String? = null,
        var propertyStatus: String? = null,
        var blockNo: String? = null,
        var fullName: String
    ): ResponseModel()