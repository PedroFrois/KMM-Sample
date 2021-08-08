package br.com.bittencourt.kmmsample.android.common

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isPresent")
fun View.isPresent(isPresent: Boolean?) {
    if (isPresent == true) {
        View.VISIBLE
    } else {
        View.GONE
    }.let { newVisibility ->
        visibility = newVisibility
    }
}
