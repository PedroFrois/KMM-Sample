package br.com.bittencourt.kmmsample.android.features.balance

import androidx.navigation.fragment.findNavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseFragment
import br.com.bittencourt.kmmsample.android.databinding.BalanceFragmentBinding
import org.koin.core.parameter.parametersOf

class BalanceFragment : BaseFragment<BalanceFragmentBinding>() {

    override val layoutId = R.layout.balance_fragment

    private val viewModel: BalanceViewModel by viewModelInternal { parametersOf(findNavController()) }

    override fun onDataBound(binding: BalanceFragmentBinding?) {
        binding?.viewModel = viewModel
    }

    override fun showScreenPopupMessage() {
        observeScreenPopupMessage(viewModel.screen)
    }
}
