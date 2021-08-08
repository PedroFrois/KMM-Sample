package br.com.bittencourt.kmmsample.provider

import br.com.bittencourt.kmmsample.model.OnboardingScreen

interface OnboardingProvider {
    suspend fun getScreenData(): OnboardingScreen
}
