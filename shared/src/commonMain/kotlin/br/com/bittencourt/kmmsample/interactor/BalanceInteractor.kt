package br.com.bittencourt.kmmsample.interactor

import br.com.bittencourt.kmmsample.interactor.common.BaseInteractor
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.model.BalanceScreen
import br.com.bittencourt.kmmsample.model.common.Status
import br.com.bittencourt.kmmsample.provider.BalanceProvider
import br.com.bittencourt.kmmsample.provider.ErrorProvider

class BalanceInteractor(
    dispatchers: DispatcherProvider,
    private val provider: BalanceProvider,
    private val errorProvider: ErrorProvider
) : BaseInteractor<BalanceScreen>(dispatchers) {

    override val initialState = BalanceScreen()

    suspend fun getScreenData() {
        updateState { it.copy(status = Status.Loading) }
        try {
            val result = provider.getScreenData()
            updateState { result.copy(status = Status.Success) }
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
