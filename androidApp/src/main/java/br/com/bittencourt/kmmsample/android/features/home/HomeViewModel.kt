package br.com.bittencourt.kmmsample.android.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseViewModel
import br.com.bittencourt.kmmsample.interactor.HomeInteractor
import br.com.bittencourt.kmmsample.model.HomeScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class HomeViewModel(
    private val interactor: HomeInteractor,
    navController: NavController?
) : BaseViewModel(navController) {

    private val _screen = MutableLiveData<HomeScreen>()
    val screen: LiveData<HomeScreen> get() = _screen

    init {
        interactor
            .subscribeToFlow()
            .onEach { _screen.value = it }
            .onStart { interactor.getScreenData() }
            .launchIn(viewModelScope)
    }

    fun navigateToBalance() {
        navigate(R.id.balanceFragment)
    }

    fun navigateToTransfer() {
        navigate(R.id.transferFragment)
    }
}
