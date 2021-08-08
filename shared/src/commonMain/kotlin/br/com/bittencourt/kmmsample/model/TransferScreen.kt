package br.com.bittencourt.kmmsample.model

import br.com.bittencourt.kmmsample.model.common.BaseScreen
import br.com.bittencourt.kmmsample.model.common.Button
import br.com.bittencourt.kmmsample.model.common.Status
import kotlinx.serialization.Serializable

@Serializable
data class TransferScreen(
    override val status: Status = Status.Loading,
    override val popUpMessage: String? = null,
    val screenTitle: String = "",
    val receiverFieldLabel: String = "",
    val receiverFieldValue: String = "",
    val transferFieldLabel: String = "",
    val transferFieldValue: Double = 0.0,
    val transferButton: Button? = null,
) : BaseScreen
