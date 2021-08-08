package br.com.bittencourt.kmmsample.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initKoin()
    }

    private val commonModule = module {
    }

    private val providersModule = module {
    }

    private val viewModelsModule = module {
    }

    private val interactorsModule = module {
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) androidLogger(Level.ERROR)

            androidContext(this@App)
            modules(
                listOf(
                    commonModule,
                    providersModule,
                    viewModelsModule,
                    interactorsModule
                )
            )
        }
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}
