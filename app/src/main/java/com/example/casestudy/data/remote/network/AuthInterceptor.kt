package com.example.casestudy.data.remote.network

import com.example.casestudy.data.local.preferences.AuthPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val prefs: AuthPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = runBlocking { prefs.tokenFlow.first() }

        val newRequest = request.newBuilder()
            .addHeader("Accept", "application/json")
            .apply {
                if (!token.isNullOrBlank()) {
                    addHeader("Authorization", "Bearer $token")
                }
            }
            .build()

        return chain.proceed(newRequest)
    }
}