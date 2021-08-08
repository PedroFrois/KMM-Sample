package br.com.bittencourt.kmmsample.interactor

import br.com.bittencourt.kmmsample.interactor.common.BaseInteractor
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.model.EntrypointScreen
import br.com.bittencourt.kmmsample.model.common.Status
import br.com.bittencourt.kmmsample.provider.ErrorProvider
import br.com.bittencourt.kmmsample.provider.OnboardingProvider

class EntrypointInteractor(
    dispatchers: DispatcherProvider,
    private val provider: OnboardingProvider,
    private val errorProvider: ErrorProvider,
) : BaseInteractor<EntrypointScreen>(
    dispatchers
) {

    override val initialState = EntrypointScreen()

    suspend fun isOnboardingSeen() {
        updateState { it.copy(status = Status.Loading) }
        kotlin.runCatching {
            val isOnboardingSeen = provider.isOnboardingSeen()
            updateState {
                it.copy(
                    status = Status.Success,
                    isOnboardingSeen = isOnboardingSeen
                )
            }
        }.getOrElse {
            updateState {
                it.copy(
                    status = Status.Error,
                    popUpMessage = errorProvider.getScreenError()
                )
            }
        }
    }
}
