package com.mikelau.loungemembership.network

import com.mikelau.loungemembership.models.Profile
import com.mikelau.loungemembership.models.UserIdRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @POST
    suspend fun getProfile(
        @Url url: String,
        @HeaderMap headers: Map<String, String>,
        @Body params: UserIdRequest
    ): Response<Profile>
}