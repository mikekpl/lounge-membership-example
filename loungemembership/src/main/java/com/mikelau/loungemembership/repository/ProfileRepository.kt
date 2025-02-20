package com.mikelau.loungemembership.repository

import android.content.Context
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import com.mikelau.loungemembership.models.Profile
import com.mikelau.loungemembership.models.UserIdRequest
import com.mikelau.loungemembership.network.ApiService
import com.mikelau.loungemembership.utils.NetworkResult
import com.mikelau.loungemembership.utils.toResultFlow
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val apiService: ApiService) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getProfile(
        context: Context,
        jwtToken: String,
        userId: String
    ): Flow<NetworkResult<Profile>> {
        return toResultFlow(context) {
            val headerMap = HashMap<String, String>().apply {
                put("Authorization", "Bearer $jwtToken")
                put("Ocp-Apim-Subscription-Key", Base64.decode("ZGUxZDBmZjgzNzQ0NDQ2OGE1ZWE4Njg5NDVhYWI3Mzg=", Base64.DEFAULT).toString(Charsets.UTF_8))
            }

            apiService.getProfile(
                url = "https://apis.alaskaair.com/1/guestservices/customermobile/account/profile",
                headers = headerMap,
                params = UserIdRequest(userId = userId)
            )
        }
    }

}