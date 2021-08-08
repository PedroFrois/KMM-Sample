package br.com.bittencourt.kmmsample.provider.remote

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

suspend inline fun <reified T> HttpClient.getJson(
    url: String
): T {
    return get(urlString = url) {
        contentType(ContentType.Application.Json)
    }
}

suspend inline fun <reified T> HttpClient.postJson(
    url: String,
    body: Any? = null
): T {
    with(Napier) {
        d("REQUEST URL: $url")
        body?.let { d("REQUEST BODY: $it") }
    } // FIXME: Verify why not logging
    return post(urlString = url) {
        contentType(ContentType.Application.Json)
        this.body = body ?: ""
    }
}
