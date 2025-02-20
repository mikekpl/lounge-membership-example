package com.mikelau.loungemembership.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

@RequiresApi(Build.VERSION_CODES.M)
inline fun <reified T> toResultFlow(
    context: Context,
    crossinline call: suspend () -> Response<T>
): Flow<NetworkResult<T>> {
    return flow {
        val isInternetConnected = OtherUtil.connectedToTheInternet(
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager,
            OtherUtil.isBuildVersionGreaterThanOrEqualTo(
                Build.VERSION_CODES.M
            )
        )
        if (isInternetConnected) {
            emit(NetworkResult.Loading(true))
            try {
                val c = call()
                if (c.isSuccessful && c.body() != null) {
                    emit(NetworkResult.Success(c.body()))
                } else {
                    emit(NetworkResult.Error(c.message()))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.toString()))
            }
        } else {
            emit(NetworkResult.Error("No Internet Connection"))
        }
    }.flowOn(Dispatchers.IO)
}