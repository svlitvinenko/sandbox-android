package ru.svlit.sandbox.feature.weather.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance
import ru.svlit.sandbox.core.base.mvvm.ViewModelProviderFactory
import ru.svlit.sandbox.core.designsystem.databinding.SimpleListBinding
import ru.svlit.sandbox.core.designsystem.item.adapter.*
import ru.svlit.sandbox.core.designsystem.item.decoration.DifferentViewTypesOffsetDecoration
import ru.svlit.sandbox.core.designsystem.item.decoration.FirstItemOffsetDecoration
import ru.svlit.sandbox.core.designsystem.item.decoration.LastItemOffsetDecoration
import ru.svlit.sandbox.feature.weather.models.presentation.CurrentWeatherArguments

class CurrentWeatherFragment : Fragment(), DIAware, EventListener {
    override val di by di()

    private lateinit var binding: SimpleListBinding
    private lateinit var adapter: ItemAdapter
    private val viewModelSupplier: () -> CurrentWeatherViewModel by instance()
    private val itemViewHolderFactory: ItemViewHolderFactory by instance()
    private lateinit var viewModel: CurrentWeatherViewModel

    override

    fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProviderFactory(viewModelSupplier) { it.initialize() }
        )[CurrentWeatherViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SimpleListBinding.inflate(inflater, container, false)
        adapter = ItemAdapter(itemViewHolderFactory, this)

        binding.mainRecyclerView.addItemDecoration(LastItemOffsetDecoration())
        binding.mainRecyclerView.addItemDecoration(FirstItemOffsetDecoration())
        binding.mainRecyclerView.addItemDecoration(DifferentViewTypesOffsetDecoration())
        binding.mainRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted { viewModel.contentState.collect { onNewContent(it) } }
        lifecycleScope.launchWhenStarted { viewModel.navigationEventFlow.collect(::onNavigationEvent) }
    }

    private fun onNewContent(items: List<Item>) {
        adapter.submitList(items)
    }

    private fun onNavigationEvent(event: NavigationEvent) {
        event.navigate(requireActivity())
    }

    override fun onEvent(event: Event) {
        Log.d(TAG, "Новое событие: $event")
        viewModel.onEvent(event)
    }

    companion object {

        const val TAG = "CurrentWeatherFragment"
        fun newInstance(arguments: CurrentWeatherArguments): CurrentWeatherFragment {
            return CurrentWeatherFragment()
        }
    }
}