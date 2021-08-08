package br.com.bittencourt.kmmsample.provider

interface EntrypointProvider {
    fun getOnboardingSeenMsg(): String
    fun getOnboardingNotSeenMsg(): String
}
