package br.com.bittencourt.kmmsample.model.common

import kotlinx.serialization.Serializable

@Serializable
sealed class Status {
    object Loading : Status()
    object Error : Status()
    object Success : Status()
    object Idle : Status()

    open val isLoading get() = this is Loading
    open val isError get() = this is Error
    open val isSuccess get() = this is Success
    open val isIdle get() = this is Idle
    open val isSuccessOrIdle get() = this is Success || this is Idle
}
