package br.com.bittencourt.kmmsample.provider.remote

import br.com.bittencourt.kmmsample.model.TransferScreen
import br.com.bittencourt.kmmsample.provider.TransferProvider
import br.com.bittencourt.kmmsample.provider.common.Endpoints
import br.com.bittencourt.kmmsample.provider.common.EndpointsBFF
import br.com.bittencourt.kmmsample.provider.common.mockHttpClient
import kotlinx.serialization.Serializable

@Serializable
data class MakeTransferRequest(val value: Double, val receiverId: String)

class TransferProviderRemote : TransferProvider {
    override suspend fun getScreenData(): TransferScreen {
        return mockHttpClient().getJson(EndpointsBFF.TRANSFER.url)
    }

    override suspend fun makeTransfer(value: Double, receiverId: String) {
        return mockHttpClient().postJson(
            url = Endpoints.TRANSFER_MAKE.url,
            body = MakeTransferRequest(value, receiverId)
        )
    }
}
