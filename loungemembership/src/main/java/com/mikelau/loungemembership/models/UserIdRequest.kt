package com.mikelau.loungemembership.models

import com.google.gson.annotations.SerializedName

data class UserIdRequest(
    @SerializedName("UserId")
    val userId: String?
)