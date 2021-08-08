package br.com.bittencourt.kmmsample.android.features.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseViewModel
import br.com.bittencourt.kmmsample.interactor.OnboardingInteractor
import br.com.bittencourt.kmmsample.model.OnboardingScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class OnboardingViewModel(
    private val interactor: OnboardingInteractor,
    navController: NavController?
) : BaseViewModel(navController) {

    private val _screen = MutableLiveData<OnboardingScreen>()
    val screen: LiveData<OnboardingScreen> get() = _screen

    init {
        interactor
            .subscribeToFlow()
            .onEach { _screen.value = it }
            .onStart { interactor.getScreenData() }
            .launchIn(viewModelScope)
    }

    fun navigateHome() {
        navigate(R.id.homeFragment)
    }
}
