package br.com.bittencourt.kmmsample.provider.common

enum class EndpointsBFF(val url: String) {
    BALANCE(url = "/balance"),
    HOME(url = "/home"),
    TRANSFER(url = "/transfer");

    companion object {
        fun from(url: String) =
            values().firstOrNull { it.url.endsWith(url, true) }
    }
}
