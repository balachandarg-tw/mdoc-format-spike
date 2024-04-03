package com.balag.mdocmocklibrary.mock

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MockApi {
    @GET("/mDocFormatMock")
    suspend fun getMockFormatVc() : Response<MockVcResponse>

    @GET("/mDocFormatMock")
    fun getMockMDocFormatVC(): Call<MockVcResponse>
}