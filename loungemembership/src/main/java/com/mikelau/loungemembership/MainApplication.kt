package com.mikelau.loungemembership

import android.app.Application
import com.mikelau.loungemembership.network.appModule
import com.mikelau.loungemembership.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    appModule,
                    networkModule
                )
            )
        }
    }
}