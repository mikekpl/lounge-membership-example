package com.mikelau.loungemembership.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mikelau.loungemembership.base.BaseViewModel
import com.mikelau.loungemembership.models.Profile
import com.mikelau.loungemembership.repository.ProfileRepository
import com.mikelau.loungemembership.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

open class ProfileViewModel(
    private val profileRepository: ProfileRepository,
    application: Application
) : BaseViewModel(application = application) {

    // Profile Data
    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: StateFlow<Profile?> get() = _profile

    fun getProfile(jwtToken: String = "", userId: String = "") {
        viewModelScope.launch {
            profileRepository.getProfile(
                context = context,
                jwtToken = jwtToken,
                userId = userId
            ).onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        try {
                            _profile.value = result.data
                        } catch (e: Exception) {
                            Log.e("Profile", "Error: ${e.message}")
                        } finally {
                            // Do Nothing
                        }
                    }

                    is NetworkResult.Loading -> {
                        // Do Nothing
                    }

                    is NetworkResult.Error -> {
                        Log.e("Profile", "Error: ${result.message}")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}