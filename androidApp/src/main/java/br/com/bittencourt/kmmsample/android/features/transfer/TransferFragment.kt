package br.com.bittencourt.kmmsample.android.features.transfer

import androidx.navigation.fragment.findNavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseFragment
import br.com.bittencourt.kmmsample.android.databinding.TransferFragmentBinding
import org.koin.core.parameter.parametersOf

class TransferFragment : BaseFragment<TransferFragmentBinding>() {

    override val layoutId = R.layout.transfer_fragment

    private val viewModel: TransferViewModel by viewModelInternal { parametersOf(findNavController()) }

    override fun onDataBound(binding: TransferFragmentBinding?) {
        binding?.viewModel = viewModel
    }
}
