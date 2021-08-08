package br.com.bittencourt.kmmsample.interactor

import br.com.bittencourt.kmmsample.interactor.common.BaseInteractor
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.model.HomeScreen
import br.com.bittencourt.kmmsample.model.common.Status
import br.com.bittencourt.kmmsample.provider.HomeProvider

class HomeInteractor(
    dispatchers: DispatcherProvider,
    private val provider: HomeProvider
) : BaseInteractor<HomeScreen>(dispatchers) {

    override val initialState = HomeScreen()

    suspend fun getScreenData() {
        updateState { it.copy(status = Status.Loading) }
        try {
            val result = provider.getScreenData()
            updateState { result.copy(status = Status.Success) }
        } catch (e: Exception) {
            updateState { it.copy(status = Status.Error) }
        }
    }
}
