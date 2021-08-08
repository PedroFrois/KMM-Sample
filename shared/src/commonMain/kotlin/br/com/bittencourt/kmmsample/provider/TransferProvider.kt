package br.com.bittencourt.kmmsample.provider

import br.com.bittencourt.kmmsample.model.TransferScreen

interface TransferProvider {
    suspend fun getScreenData(): TransferScreen
    suspend fun makeTransfer(value: Double, receiverId: String)
}
