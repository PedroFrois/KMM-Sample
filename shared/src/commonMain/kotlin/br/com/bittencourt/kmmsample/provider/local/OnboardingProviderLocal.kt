package br.com.bittencourt.kmmsample.provider.local

import br.com.bittencourt.kmmsample.provider.OnboardingProvider

class OnboardingProviderLocal : OnboardingProvider {
    override fun isOnboardingSeen(): Boolean {
        return true // fixme
    }
}
