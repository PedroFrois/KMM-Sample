package br.com.bittencourt.kmmsample.provider.synthetic

import br.com.bittencourt.kmmsample.provider.EntrypointProvider

class EntrypointProviderSynthetic : EntrypointProvider {
    override fun getOnboardingSeenMsg(): String {
        return "Onboarding já visualizado"
    }

    override fun getOnboardingNotSeenMsg(): String {
        return "Navegando para onboarding"
    }
}
