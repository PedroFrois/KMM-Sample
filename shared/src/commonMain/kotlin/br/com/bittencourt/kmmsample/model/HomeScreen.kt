package br.com.bittencourt.kmmsample.model

import br.com.bittencourt.kmmsample.model.common.BaseScreen
import br.com.bittencourt.kmmsample.model.common.Status
import kotlinx.serialization.Serializable

@Serializable
data class HomeScreen(
    override val status: Status = Status.Loading,
    override val popUpMessage: String? = null,
    val screenTitle: String = "",
    val balanceButtonTitle: String = "",
    val transferButtonTitle: String = "",
) : BaseScreen
