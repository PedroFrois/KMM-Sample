package br.com.bittencourt.kmmsample.provider.common.clients

import br.com.bittencourt.kmmsample.model.BalanceScreen
import br.com.bittencourt.kmmsample.model.HomeScreen
import br.com.bittencourt.kmmsample.model.OnboardingScreen
import br.com.bittencourt.kmmsample.model.TransferScreen
import br.com.bittencourt.kmmsample.model.common.BaseScreen
import br.com.bittencourt.kmmsample.model.common.Button
import br.com.bittencourt.kmmsample.provider.common.Endpoints
import br.com.bittencourt.kmmsample.provider.common.EndpointsBFF
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun mockHttpClient(withLog: Boolean = true): HttpClient {
    return HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                respond(
                    content = getJsonResponse("/" + request.url.encodedPath),
                    HttpStatusCode.OK,
                    headersOf("Content-Type", ContentType.Application.Json.toString())
                )
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }

        if (withLog) install(Logging) {
            level = LogLevel.HEADERS
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "MockHttpClient", message = message)
                }
            }
        }
    }
}

private fun getJsonResponse(url: String): String =
    when (Endpoints.from(url)) {
        Endpoints.TRANSFER_MAKE -> "Transferencia realizada com sucesso!"
        null -> getBffJsonResponse(url)
    }

private fun getBffJsonResponse(url: String) = when (EndpointsBFF.from(url)) {
    EndpointsBFF.BALANCE -> {
        BalanceScreen(
            screenTitle = "Consulta Saldo",
            balanceLabel = "Saldo",
            balanceValue = "R$ 4500.97"
        ).toJson<BalanceScreen>()
    }
    EndpointsBFF.HOME -> {
        HomeScreen(
            screenTitle = "Home",
            balanceButtonTitle = "Consultar Saldo",
            transferButtonTitle = "Fazer Transfer??ncia"
        ).toJson<HomeScreen>()
    }
    EndpointsBFF.TRANSFER -> {
        TransferScreen(
            screenTitle = "Transfer??ncia",
            receiverFieldLabel = "CPF do Destinat??rio",
            receiverFieldValue = "",
            transferFieldLabel = "Valor a ser transferido:",
            transferFieldValue = 0.0,
            transferButton = Button("Transferir")
        ).toJson<TransferScreen>()
    }
    EndpointsBFF.ONBOARDING -> {
        OnboardingScreen(
            screenTitle = "Onboarding",
            onboardingMsg = "Esse aplicativo tem por fun????o exemplificar a separa????o de camadas e estruturas proposta na monografia." +
                " Al??m de tamb??m utilizar o Kotlin Multiplatform Mobile para separar a aplica????o em m??dulos compartilhado e n??o compartilhado.",
            closeButton = Button("Fechar onboarding")
        ).toJson<OnboardingScreen>()
    }
    null -> throw error("Unhandled url: $url")
}

private inline fun <reified T> BaseScreen.toJson(): String {
    return Json.encodeToString(this as T)
}
