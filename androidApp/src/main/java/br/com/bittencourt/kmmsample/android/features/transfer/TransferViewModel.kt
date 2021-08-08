package br.com.bittencourt.kmmsample.android.features.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.bittencourt.kmmsample.android.common.BaseViewModel
import br.com.bittencourt.kmmsample.interactor.TransferInteractor
import br.com.bittencourt.kmmsample.model.TransferScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class TransferViewModel(
    private val interactor: TransferInteractor,
    navController: NavController?
) : BaseViewModel(navController) {

    private val _screen = MutableLiveData<TransferScreen>()
    val screen: LiveData<TransferScreen> get() = _screen

    init {
        interactor
            .subscribeToFlow()
            .onEach { _screen.value = it }
            .onStart { interactor.getScreenData() }
            .launchIn(viewModelScope)
    }

    fun transfer() {
        viewModelScope.launch { interactor.makeTransfer(0.0, "") } // fixme
    }
}
