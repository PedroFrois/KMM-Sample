package br.com.bittencourt.kmmsample.android.features.entrypoint

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseViewModel
import br.com.bittencourt.kmmsample.interactor.EntrypointInteractor
import br.com.bittencourt.kmmsample.model.EntrypointScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class EntrypointViewModel(
    private val interactor: EntrypointInteractor,
    navController: NavController?
) : BaseViewModel(navController) {

    private val _screen = MutableLiveData<EntrypointScreen>()
    val screen: LiveData<EntrypointScreen> get() = _screen

    init {
        interactor
            .subscribeToFlow()
            .onEach { handleStateChange(it) }
            .onStart { interactor.isOnboardingSeen() }
            .launchIn(viewModelScope)
    }

    private fun handleStateChange(entrypointScreen: EntrypointScreen) {
        _screen.value = entrypointScreen
        navigateWhenOnboardingVisualizationDataIsLoaded(entrypointScreen)
    }

    private fun navigateWhenOnboardingVisualizationDataIsLoaded(entrypointScreen: EntrypointScreen) {
        entrypointScreen.isOnboardingSeen?.let { isOnboardingSeen ->
            val navOptions = NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setPopUpTo(startDestination ?: R.id.nav_graph, true)
                .build()

            if (isOnboardingSeen) {
                R.id.homeFragment
            } else {
                R.id.onboardingFragment
            }.let { destinationId ->
                navigate(destinationId, navOptions)
            }
        }
    }
}
