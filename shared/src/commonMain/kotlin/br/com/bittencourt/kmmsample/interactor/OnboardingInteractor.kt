package br.com.bittencourt.kmmsample.interactor

import br.com.bittencourt.kmmsample.interactor.common.BaseInteractor
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.model.OnboardingScreen
import br.com.bittencourt.kmmsample.model.common.Status
import br.com.bittencourt.kmmsample.provider.ErrorProvider
import br.com.bittencourt.kmmsample.provider.OnboardingProvider
import br.com.bittencourt.kmmsample.provider.OnboardingVisualizationProvider

class OnboardingInteractor(
    dispatchers: DispatcherProvider,
    private val provider: OnboardingProvider,
    private val onboardingVisualizationProvider: OnboardingVisualizationProvider,
    private val errorProvider: ErrorProvider
) : BaseInteractor<OnboardingScreen>(dispatchers) {

    override val initialState = OnboardingScreen()

    suspend fun getScreenData() {
        updateState { it.copy(status = Status.Loading) }
        try {
            val result = provider.getScreenData()
            updateState { result.copy(status = Status.Success) }
            onboardingVisualizationProvider.setOnboardingAsSeen()
            updateState {
                it.copy(
                    popupMessage = "Onboarding Visualizado"
                )
            }
        } catch (e: Exception) {
            updateState {
                it.copy(
                    status = Status.Error,
                    popupMessage = errorProvider.getScreenError()
                )
            }
        }
    }
}
