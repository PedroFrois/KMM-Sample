package br.com.bittencourt.kmmsample.android.features.home

import androidx.navigation.fragment.findNavController
import br.com.bittencourt.kmmsample.android.R
import br.com.bittencourt.kmmsample.android.common.BaseFragment
import br.com.bittencourt.kmmsample.android.databinding.HomeFragmentBinding
import org.koin.core.parameter.parametersOf

class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    override val layoutId = R.layout.home_fragment

    private val viewModel: HomeViewModel by viewModelInternal { parametersOf(findNavController()) }

    override fun onDataBound(binding: HomeFragmentBinding?) {
        binding?.viewModel = viewModel
    }
}
