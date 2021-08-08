package br.com.bittencourt.kmmsample.android.features.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.bittencourt.kmmsample.android.common.BaseViewModel
import br.com.bittencourt.kmmsample.interactor.BalanceInteractor
import br.com.bittencourt.kmmsample.model.BalanceScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class BalanceViewModel(
    private val interactor: BalanceInteractor,
    navController: NavController?
) : BaseViewModel(navController) {

    private val _screen = MutableLiveData<BalanceScreen>()
    val screen: LiveData<BalanceScreen> get() = _screen

    init {
        interactor
            .subscribeToFlow()
            .onEach { _screen.value = it }
            .onStart { interactor.getScreenData() }
            .launchIn(viewModelScope)
    }
}
