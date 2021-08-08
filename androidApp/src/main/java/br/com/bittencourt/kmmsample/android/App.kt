package br.com.bittencourt.kmmsample.android

import android.app.Application
import androidx.navigation.NavController
import br.com.bittencourt.kmmsample.android.features.balance.BalanceViewModel
import br.com.bittencourt.kmmsample.android.features.entrypoint.EntrypointViewModel
import br.com.bittencourt.kmmsample.android.features.home.HomeViewModel
import br.com.bittencourt.kmmsample.android.features.transfer.TransferViewModel
import br.com.bittencourt.kmmsample.interactor.BalanceInteractor
import br.com.bittencourt.kmmsample.interactor.EntrypointInteractor
import br.com.bittencourt.kmmsample.interactor.HomeInteractor
import br.com.bittencourt.kmmsample.interactor.TransferInteractor
import br.com.bittencourt.kmmsample.interactor.common.DefaultDispatcherProvider
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.provider.*
import br.com.bittencourt.kmmsample.provider.local.OnboardingProviderLocal
import br.com.bittencourt.kmmsample.provider.remote.BalanceProviderRemote
import br.com.bittencourt.kmmsample.provider.remote.HomeProviderRemote
import br.com.bittencourt.kmmsample.provider.remote.TransferProviderRemote
import br.com.bittencourt.kmmsample.provider.synthetic.ErrorProviderSynthetic
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
    }

    private val providersModule = module {
        factory<BalanceProvider> { BalanceProviderRemote() }
        factory<ErrorProvider> { ErrorProviderSynthetic() }
        factory<HomeProvider> { HomeProviderRemote() }
        factory<OnboardingProvider> { OnboardingProviderLocal() }
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
