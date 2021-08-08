package br.com.bittencourt.kmmsample.provider

import br.com.bittencourt.kmmsample.model.HomeScreen

interface HomeProvider {
    suspend fun getScreenData(): HomeScreen
}
