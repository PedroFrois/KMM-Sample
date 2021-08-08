package br.com.bittencourt.kmmsample.model.common

import kotlinx.serialization.Serializable

@Serializable
data class Button(val title: String, val status: Status = Status.Idle)
