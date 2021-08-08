package br.com.bittencourt.kmmsample.provider.remote

import br.com.bittencourt.kmmsample.model.HomeScreen
import br.com.bittencourt.kmmsample.provider.HomeProvider
import br.com.bittencourt.kmmsample.provider.common.EndpointsBFF
import br.com.bittencourt.kmmsample.provider.common.mockHttpClient

class HomeProviderRemote : HomeProvider {
    override suspend fun getScreenData(): HomeScreen {
        return mockHttpClient().getJson(EndpointsBFF.HOME.url)
    }
}
