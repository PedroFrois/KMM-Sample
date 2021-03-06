package br.com.bittencourt.kmmsample.android

import android.app.Application
import androidx.navigation.NavController
import br.com.bittencourt.kmmsample.android.features.balance.BalanceViewModel
import br.com.bittencourt.kmmsample.android.features.entrypoint.EntrypointViewModel
import br.com.bittencourt.kmmsample.android.features.home.HomeViewModel
import br.com.bittencourt.kmmsample.android.features.onboarding.OnboardingViewModel
import br.com.bittencourt.kmmsample.android.features.transfer.TransferViewModel
import br.com.bittencourt.kmmsample.interactor.*
import br.com.bittencourt.kmmsample.interactor.common.DefaultDispatcherProvider
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.provider.*
import br.com.bittencourt.kmmsample.provider.local.OnboardingVisualizationProviderLocal
import br.com.bittencourt.kmmsample.provider.remote.BalanceProviderRemote
import br.com.bittencourt.kmmsample.provider.remote.HomeProviderRemote
import br.com.bittencourt.kmmsample.provider.remote.OnboardingProviderRemote
import br.com.bittencourt.kmmsample.provider.remote.TransferProviderRemote
import br.com.bittencourt.kmmsample.provider.synthetic.EntrypointProviderSynthetic
import br.com.bittencourt.kmmsample.provider.synthetic.ErrorProviderSynthetic
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
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
        single<DispatcherProvider> { DefaultDispatcherProvider() }
        single<Settings> {
            AndroidSettings(
                applicationContext.getSharedPreferences(
                    "kmm_bittencourt_android_pref",
                    MODE_PRIVATE
                )
            )
        }
    }

    private val providersModule = module {
        factory<BalanceProvider> { BalanceProviderRemote() }
        factory<EntrypointProvider> { EntrypointProviderSynthetic() }
        factory<ErrorProvider> { ErrorProviderSynthetic() }
        factory<HomeProvider> { HomeProviderRemote() }
        factory<OnboardingProvider> { OnboardingProviderRemote() }
        factory<OnboardingVisualizationProvider> {
            OnboardingVisualizationProviderLocal(
                multiplatformSettings = get(),
            )
        }
        factory<TransferProvider> { TransferProviderRemote() }
    }

    private val viewModelsModule = module {
        viewModel { (navController: NavController?) ->
            BalanceViewModel(
                interactor = get(),
                navController = navController
            )
        }
        viewModel { (navController: NavController?) ->
            EntrypointViewModel(
                interactor = get(),
                navController = navController
            )
        }
        viewModel { (navController: NavController?) ->
            HomeViewModel(
                interactor = get(),
                navController = navController
            )
        }
        viewModel { (navController: NavController?) ->
            TransferViewModel(
                interactor = get(),
                navController = navController
            )
        }
        viewModel { (navController: NavController?) ->
            OnboardingViewModel(
                interactor = get(),
                navController = navController
            )
        }
    }

    private val interactorsModule = module {
        factory {
            BalanceInteractor(
                dispatchers = get(),
                provider = get(),
                errorProvider = get()
            )
        }
        factory {
            EntrypointInteractor(
                dispatchers = get(),
                provider = get(),
                entrypointProvider = get(),
                errorProvider = get()
            )
        }
        factory {
            HomeInteractor(
                dispatchers = get(),
                provider = get(),
            )
        }
        factory {
            TransferInteractor(
                dispatchers = get(),
                provider = get(),
                errorProvider = get()
            )
        }
        factory {
            OnboardingInteractor(
                dispatchers = get(),
                provider = get(),
                onboardingVisualizationProvider = get(),
                errorProvider = get(),
            )
        }
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
