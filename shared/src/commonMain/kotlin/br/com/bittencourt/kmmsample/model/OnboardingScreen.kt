package br.com.bittencourt.kmmsample.model

import br.com.bittencourt.kmmsample.model.common.BaseScreen
import br.com.bittencourt.kmmsample.model.common.Button
import br.com.bittencourt.kmmsample.model.common.Status
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingScreen(
    override val status: Status = Status.Loading,
    override val popupMessage: String? = null,
    val screenTitle: String = "",
    val onboardingMsg: String = "",
    val closeButton: Button? = null,
) : BaseScreen
