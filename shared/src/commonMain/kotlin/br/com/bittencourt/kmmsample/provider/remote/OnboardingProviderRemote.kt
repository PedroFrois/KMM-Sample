package br.com.bittencourt.kmmsample.provider.remote

import br.com.bittencourt.kmmsample.model.OnboardingScreen
import br.com.bittencourt.kmmsample.provider.OnboardingProvider
import br.com.bittencourt.kmmsample.provider.common.EndpointsBFF
import br.com.bittencourt.kmmsample.provider.common.mockHttpClient

class OnboardingProviderRemote : OnboardingProvider {
    override suspend fun getScreenData(): OnboardingScreen {
        return mockHttpClient().getJson(EndpointsBFF.ONBOARDING.url)
    }
}
