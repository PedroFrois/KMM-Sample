package br.com.bittencourt.kmmsample.model

import br.com.bittencourt.kmmsample.model.common.BaseScreen
import br.com.bittencourt.kmmsample.model.common.Status
import kotlinx.serialization.Serializable

@Serializable
data class BalanceScreen(
    override val status: Status = Status.Loading,
    override val popupMessage: String? = null,
    val screenTitle: String = "",
    val balanceLabel: String = "",
    val balanceValue: String = "",
) : BaseScreen
