package com.mikelau.loungemembership.network

import com.mikelau.loungemembership.repository.ProfileRepository
import com.mikelau.loungemembership.viewmodels.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val loungeMembershipAppModule = module {

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