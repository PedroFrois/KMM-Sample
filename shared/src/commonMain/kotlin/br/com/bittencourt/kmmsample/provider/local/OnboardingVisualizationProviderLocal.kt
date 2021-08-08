package br.com.bittencourt.kmmsample.provider.local

import br.com.bittencourt.kmmsample.provider.OnboardingVisualizationProvider

class OnboardingVisualizationProviderLocal : OnboardingVisualizationProvider {
    override fun isOnboardingSeen(): Boolean {
        return false // fixme
    }

    override fun setOnboardingAsSeen() {
        TODO("Not yet implemented")
    }
}
