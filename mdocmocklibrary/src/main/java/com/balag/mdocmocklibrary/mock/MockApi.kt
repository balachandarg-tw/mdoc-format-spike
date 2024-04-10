package com.balag.mdocmocklibrary.mock

import IssuerAPIResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MockApi {
    @GET("/mDocFormatMock")
    suspend fun getMockFormatVc() : Response<IssuerAPIResponse>

}