package br.com.bittencourt.kmmsample.interactor.common

import br.com.bittencourt.kmmsample.model.common.BaseScreen
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

abstract class BaseInteractor<S : BaseScreen>(
    private val dispatchers: DispatcherProvider,
) {

    abstract val initialState: S

    val currentState: S get() = stateFlow.value

    private val stateFlow: MutableStateFlow<S> by lazy { MutableStateFlow(initialState) }

    fun subscribeToFlow() = stateFlow

    suspend fun updateState(updatedState: (S) -> S) {
        try {
            withContext(dispatchers.default) {
                val newState = updatedState(currentState)
                if (newState != currentState) { // FIXME: 06/08/21
                    Napier.v("\n\nUpdating state: $newState")
                    stateFlow.emit(newState)
                }
            }
        } catch (e: Throwable) {
            Napier.e("Error during state update:", e)
        }
    }
}
