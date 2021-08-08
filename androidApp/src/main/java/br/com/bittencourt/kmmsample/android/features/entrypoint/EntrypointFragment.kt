package br.com.bittencourt.kmmsample.android.features.entrypoint

import androidx.navigation.fragment.findNavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseFragment
import br.com.bittencourt.kmmsample.android.databinding.EntrypointFragmentBinding
import org.koin.core.parameter.parametersOf

class EntrypointFragment : BaseFragment<EntrypointFragmentBinding>() {

    override val layoutId = R.layout.entrypoint_fragment

    private val viewModel: EntrypointViewModel by viewModelInternal { parametersOf(findNavController()) }

    override fun onDataBound(binding: EntrypointFragmentBinding?) {
        binding?.viewmodel = viewModel
    }

    override fun showScreenPopupMessage() {
        observeScreenPopupMessage(viewModel.screen)
    }
}
