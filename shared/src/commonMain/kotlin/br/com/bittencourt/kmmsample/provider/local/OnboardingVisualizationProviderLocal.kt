package br.com.bittencourt.kmmsample.provider.local

import br.com.bittencourt.kmmsample.provider.OnboardingVisualizationProvider
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class OnboardingVisualizationProviderLocal(
    private val multiplatformSettings: Settings
) : OnboardingVisualizationProvider {

    override fun isOnboardingSeen(): Boolean {
        return multiplatformSettings.getBoolean(
            key = KEY_KMM_ONBOARDING_CACHE_BITTENCOURT_APP,
            defaultValue = false
        )
    }

    override fun setOnboardingAsSeen() {
        multiplatformSettings.set(
            key = KEY_KMM_ONBOARDING_CACHE_BITTENCOURT_APP,
            value = true
        )
    }

    private companion object {
        private const val KEY_KMM_ONBOARDING_CACHE_BITTENCOURT_APP =
            "key_kmm_onboarding_cache_bittencourt_app"
    }
}
