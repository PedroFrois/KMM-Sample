package br.com.bittencourt.kmmsample.android.features.onboarding

import androidx.navigation.fragment.findNavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseFragment
import br.com.bittencourt.kmmsample.android.databinding.OnboardingFragmentBinding
import org.koin.core.parameter.parametersOf

class OnboardingFragment : BaseFragment<OnboardingFragmentBinding>() {

    override val layoutId = R.layout.onboarding_fragment

    private val viewModel: OnboardingViewModel by viewModelInternal { parametersOf(findNavController()) }

    override fun onDataBound(binding: OnboardingFragmentBinding?) {
        binding?.viewModel = viewModel
    }

    override fun showScreenPopupMessage() {
        observeScreenPopupMessage(viewModel.screen)
    }
}
