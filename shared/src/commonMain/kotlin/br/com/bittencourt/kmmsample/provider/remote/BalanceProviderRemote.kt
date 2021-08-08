package br.com.bittencourt.kmmsample.provider.remote

import br.com.bittencourt.kmmsample.model.BalanceScreen
import br.com.bittencourt.kmmsample.provider.BalanceProvider
import br.com.bittencourt.kmmsample.provider.common.EndpointsBFF
import br.com.bittencourt.kmmsample.provider.common.clients.mockHttpClient

class BalanceProviderRemote : BalanceProvider {
    override suspend fun getScreenData(): BalanceScreen {
        return mockHttpClient().getJson(EndpointsBFF.BALANCE.url)
    }
}
