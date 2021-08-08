package br.com.bittencourt.kmmsample.android.common

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions

abstract class BaseViewModel(private val navController: NavController? = null) : ViewModel() {
    val startDestination: Int?
        get() = navController?.graph?.startDestination

    fun navigate(destinationId: Int, navOptions: NavOptions? = null) {
        navController?.navigate(destinationId, null, navOptions)
    }
}
