package br.com.bittencourt.kmmsample.provider

import br.com.bittencourt.kmmsample.model.BalanceScreen

interface BalanceProvider {
    suspend fun getScreenData(): BalanceScreen
}
