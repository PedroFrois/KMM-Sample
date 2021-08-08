package br.com.bittencourt.kmmsample.provider

interface OnboardingVisualizationProvider {
    fun isOnboardingSeen(): Boolean
    fun setOnboardingAsSeen()
}
