package com.mikelau.loungemembershipexample

import android.app.Application
import com.mikelau.loungemembership.network.loungeMembershipAppModule
import com.mikelau.loungemembership.network.loungeMembershipNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    loungeMembershipAppModule,
                    loungeMembershipNetworkModule
                )
            )
        }
    }
}