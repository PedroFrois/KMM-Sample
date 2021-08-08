package br.com.bittencourt.kmmsample.provider

interface ErrorProvider {
    fun getScreenError(): String
    fun getPostError(): String
}
