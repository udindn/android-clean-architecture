package com.test.kumparan.app

import com.arrayyan.context.FastApp
import com.test.core.di.databaseModule
import com.test.core.di.networkModule
import com.test.core.di.repositoryModule
import com.test.kumparan.di.useCaseModule
import com.test.kumparan.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : FastApp() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Application)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}