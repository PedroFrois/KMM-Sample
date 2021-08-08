package br.com.bittencourt.kmmsample.interactor

import br.com.bittencourt.kmmsample.interactor.common.BaseInteractor
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.model.EntrypointScreen
import br.com.bittencourt.kmmsample.model.common.Status
import br.com.bittencourt.kmmsample.provider.EntrypointProvider
import br.com.bittencourt.kmmsample.provider.ErrorProvider
import br.com.bittencourt.kmmsample.provider.OnboardingVisualizationProvider

class EntrypointInteractor(
    dispatchers: DispatcherProvider,
    private val provider: OnboardingVisualizationProvider,
    private val entrypointProvider: EntrypointProvider,
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
                    popupMessage = if (isOnboardingSeen) {
                        entrypointProvider.getOnboardingSeenMsg()
                    } else {
                        entrypointProvider.getOnboardingNotSeenMsg()
                    },
                    isOnboardingSeen = isOnboardingSeen
                )
            }
        }.getOrElse {
            updateState {
                it.copy(
                    status = Status.Error,
                    popupMessage = errorProvider.getScreenError()
                )
            }
        }
    }
}
