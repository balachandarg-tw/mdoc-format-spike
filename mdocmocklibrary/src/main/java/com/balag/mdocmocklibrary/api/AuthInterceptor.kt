package com.balag.mdocmocklibrary.api

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context, accessToken: String?) : Interceptor {

    val sharedPreference =  context.getSharedPreferences("DUMMY_DATA", Context.MODE_PRIVATE)

    val authToken = sharedPreference.getString("access_token", "def") //Pass accessToken

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = authToken
        Log.d("balgggg->Token in Intercepter", token.toString())
        val request = chain.request()
        if (!token.isNullOrEmpty()) {
            val newRequest = request
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }
}