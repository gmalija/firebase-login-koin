package com.gmalija.access

import android.app.Application
import com.gmalija.access.di.repositoryModule
import com.gmalija.access.di.useCaseModule
import com.gmalija.access.di.viewModelModule
import com.gmalija.core.di.firebaseModule
import com.gmalija.core.di.googleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class Bootstrap : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        // Adding Koin modules to our application
        startKoin {
            androidLogger()
            androidContext(this@Bootstrap)
            modules(listOf(
                firebaseModule,
                googleModule,
                repositoryModule,
                useCaseModule,
                viewModelModule)
            )
        }

    }

}