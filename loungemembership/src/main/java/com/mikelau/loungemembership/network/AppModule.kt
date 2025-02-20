package com.mikelau.loungemembership.network

import com.mikelau.loungemembership.repository.ProfileRepository
import com.mikelau.loungemembership.viewmodels.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory {
        ProfileRepository(
            apiService = get(),
        )
    }

    viewModel {
        ProfileViewModel(
            profileRepository = get(),
            application = get()
        )
    }

}