package br.com.bittencourt.kmmsample.provider.common

enum class Endpoints(val url: String) {
    TRANSFER_MAKE(url = "/make-transfer");

    companion object {
        fun from(url: String) =
            values().firstOrNull { it.url.endsWith(url, true) }
    }
}
