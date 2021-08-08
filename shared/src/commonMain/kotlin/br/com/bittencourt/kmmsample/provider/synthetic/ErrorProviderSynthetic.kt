package br.com.bittencourt.kmmsample.provider.synthetic

import br.com.bittencourt.kmmsample.provider.ErrorProvider

class ErrorProviderSynthetic : ErrorProvider {
    override fun getScreenError(): String {
        return "Erro ao carregar dados da tela"
    }

    override fun getPostError(): String {
        return "Não foi possível realizar essa ação"
    }
}
