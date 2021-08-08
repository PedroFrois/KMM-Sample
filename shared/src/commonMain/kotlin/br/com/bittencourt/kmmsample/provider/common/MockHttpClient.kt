package br.com.bittencourt.kmmsample.provider.common

import br.com.bittencourt.kmmsample.model.common.BaseScreen
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
        Endpoints.TRANSFER_MAKE -> "{}"
        null -> getBffJsonResponse(url)
    }

private fun getBffJsonResponse(url: String) = when (EndpointsBFF.from(url)) {
    EndpointsBFF.BALANCE -> {
        ""
    }
    EndpointsBFF.HOME -> {
        ""
    }
    EndpointsBFF.TRANSFER -> {
        ""
    }
    null -> throw error("Unhandled url: $url")
}

private inline fun <reified T> BaseScreen.toJson(): String {
    return Json.encodeToString(this as T)
}
