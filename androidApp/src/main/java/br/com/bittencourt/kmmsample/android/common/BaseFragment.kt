package br.com.bittencourt.kmmsample.android.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.bittencourt.kmmsample.model.common.BaseScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    /**
     * The layout id for the [ViewDataBinding] of [T]
     */
    abstract val layoutId: Int

    /**
     * This reference must not be used before [onViewCreated] or a [NullPointerException]
     * will be thrown.
     */
    private var binding: T? = null

    private var viewModel: ViewModel? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding?.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.lifecycleOwner = viewLifecycleOwner
        onDataBound(binding)
        showScreenPopupMessage()
        (viewModel as? LifecycleObserver)?.let { lifecycle.addObserver(it) }
    }

    /**
     *
     * @param binding a binding for [layoutId] already tied to the fragment's lifecycle
     */
    abstract fun onDataBound(binding: T?)

    abstract fun showScreenPopupMessage()

    /**
     * Lazy getByClass a viewModel instance
     *
     * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
     * @param parameters - parameters to pass to the BeanDefinition
     */
    protected inline fun <reified T : ViewModel> viewModelInternal(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
    ): Lazy<T> = lazy { getViewModelInternal(qualifier, parameters) }

    /**
     * Get a viewModel instance
     *
     * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
     * @param parameters - parameters to pass to the BeanDefinition
     */
    protected inline fun <reified T : ViewModel> getViewModelInternal(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
    ): T {
        return getViewModelInternal(T::class, qualifier, parameters)
    }

    /**
     * Lazy getByClass a viewModel instance
     *
     * @param clazz - Class of the BeanDefinition to retrieve
     * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
     * @param parameters - parameters to pass to the BeanDefinition
     */
    protected fun <T : ViewModel> getViewModelInternal(
        clazz: KClass<T>,
        qualifier: Qualifier? = null,
        parameters: ParametersDefinition? = null
    ): T {
        val viewModel = getViewModel(qualifier = qualifier, clazz = clazz, parameters = parameters)
        this.viewModel = viewModel
        return viewModel
    }

    protected inline fun <reified T : BaseScreen> Fragment.observeScreenPopupMessage(
        screenLiveData: LiveData<T>,
    ) {
        screenLiveData.observe(viewLifecycleOwner, {
            it.popupMessage?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
