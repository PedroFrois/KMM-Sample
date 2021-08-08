package br.com.bittencourt.kmmsample.model.common

interface BaseScreen {
    val status: Status
    val popUpMessage: String? // TODO: 08/08/21 Implement message popUp
}
