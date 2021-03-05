package ru.svlit.sandbox.feature.host.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance
import ru.svlit.sandbox.core.base.mvvm.ViewModelProviderFactory
import ru.svlit.sandbox.core.designsystem.item.adapter.*
import ru.svlit.sandbox.feature.host.databinding.HostBinding
import ru.svlit.sandbox.feature.host.databinding.HostToolbarCollapsedContentBinding
import ru.svlit.sandbox.feature.host.databinding.HostToolbarExpandedContentBinding

/**
 * Фрагмент разводного экрана.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
@FlowPreview
@ExperimentalCoroutinesApi
class HostFragment : Fragment(), EventListener, DIAware {

    override val di: DI by di()
    private val viewModelSupplier: () -> HostViewModel by instance()
    private val itemViewHolderFactory: ItemViewHolderFactory by instance()

    private lateinit var viewModel: HostViewModel

    private lateinit var binding: HostBinding
    private lateinit var collapsedToolbarBinding: HostToolbarCollapsedContentBinding
    private lateinit var expandedToolbarBinding: HostToolbarExpandedContentBinding
    private lateinit var topAdapter: ItemAdapter
    private lateinit var mainAdapter: ItemAdapter
    private lateinit var bottomAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(viewModelStore, ViewModelProviderFactory(
            viewModelSupplier,
            { it.initialize() }
        ))[HostViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = HostBinding.inflate(inflater, container, false)
        collapsedToolbarBinding = HostToolbarCollapsedContentBinding.bind(binding.appBarLayout.getCollapsedView())
        expandedToolbarBinding = HostToolbarExpandedContentBinding.bind(binding.appBarLayout.getExpandedView())
        topAdapter = ItemAdapter(itemViewHolderFactory, this)
        mainAdapter = ItemAdapter(itemViewHolderFactory, this)
        bottomAdapter = ItemAdapter(itemViewHolderFactory, this)
        expandedToolbarBinding.topRecyclerView.adapter = topAdapter
        binding.mainRecyclerView.adapter = mainAdapter
        binding.bottomRecyclerView.adapter = bottomAdapter
        lifecycleScope.launchWhenStarted { viewModel.contentStateFlow.collect { onNewContent(it) } }
        lifecycleScope.launchWhenStarted { viewModel.navigationEventFlow.collect { onNavigationEvent(it) } }
        return binding.root
    }

    private fun onNewContent(content: HostContent) {
        topAdapter.submitList(content.topItems)
        mainAdapter.submitList(content.mainItems)
        bottomAdapter.submitList(content.bottomItems)
    }

    private fun onNavigationEvent(navigationEvent: NavigationEvent) {
        navigationEvent.navigate(requireActivity())
    }

    override fun onEvent(event: Event) {
        viewModel.onEvent(event)
    }

    companion object {

        const val TAG = "HostFragment"

        fun newInstance(): HostFragment = HostFragment()
    }
}