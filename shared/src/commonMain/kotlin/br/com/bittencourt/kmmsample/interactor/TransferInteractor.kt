package br.com.bittencourt.kmmsample.interactor

import br.com.bittencourt.kmmsample.interactor.common.BaseInteractor
import br.com.bittencourt.kmmsample.interactor.common.DispatcherProvider
import br.com.bittencourt.kmmsample.model.TransferScreen
import br.com.bittencourt.kmmsample.model.common.Status
import br.com.bittencourt.kmmsample.provider.ErrorProvider
import br.com.bittencourt.kmmsample.provider.TransferProvider

class TransferInteractor(
    dispatchers: DispatcherProvider,
    private val provider: TransferProvider,
    private val errorProvider: ErrorProvider
) : BaseInteractor<TransferScreen>(dispatchers) {

    override val initialState = TransferScreen()

    suspend fun getScreenData() { // FIXME: 06/08/21  
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

    suspend fun makeTransfer(value: Double, receiverId: String) {
        updateState { it.copy(transferButton = it.transferButton?.copy(status = Status.Loading)) }
        try {
            provider.makeTransfer(value, receiverId)
            updateState {
                it.copy(
                    transferButton = it.transferButton?.copy(status = Status.Success),
                    popupMessage = "Transferência realizada com sucesso!"
                )
            }
        } catch (e: Exception) {
            updateState {
                it.copy(
                    transferButton = it.transferButton?.copy(status = Status.Loading),
                    popupMessage = errorProvider.getPostError()
                )
            }
        }
    }
}
