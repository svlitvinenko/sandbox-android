package ru.svlit.sandbox.feature.fines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
import ru.svlit.sandbox.core.designsystem.item.decoration.SameViewTypesOffsetDecoration
import ru.svlit.sandbox.feature.fines.R

/**
 * Фрагмент Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class FinesFragment : Fragment(), DIAware, EventListener {

    override val di by di()

    private lateinit var binding: SimpleListBinding
    private val itemViewHolderFactory: ItemViewHolderFactory by instance()

    private lateinit var mainAdapter: ItemAdapter
    private lateinit var bottomAdapter: ItemAdapter

    private val viewModelSupplier: () -> FinesViewModel by instance()
    private lateinit var viewModel: FinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            viewModelStore,
            ViewModelProviderFactory(viewModelSupplier) { it.initialize(finesArguments) }
        )[FinesViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = SimpleListBinding.inflate(inflater, container, false)
        setupViews()
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        lifecycleScope.launchWhenStarted { viewModel.contentState.collect(::onNewContent) }
        lifecycleScope.launchWhenStarted { viewModel.navigationEventFlow.collect(::onNavigationEvent) }
    }

    override fun onEvent(event: Event) {
        viewModel.onEvent(event)
    }

    private fun onNewContent(content: FinesContent) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = content.title.getText(requireContext())
        mainAdapter.submitList(content.mainItems)
        bottomAdapter.submitList(content.bottomItems)
    }

    private fun onNavigationEvent(event: NavigationEvent) {
        event.navigate(requireActivity())
    }

    private fun setupViews() {
        mainAdapter = ItemAdapter(itemViewHolderFactory, this)
        bottomAdapter = ItemAdapter(itemViewHolderFactory, this)

        binding.mainRecyclerView.adapter = mainAdapter
        binding.mainRecyclerView.addItemDecoration(FirstItemOffsetDecoration(R.dimen.margin_medium))
        binding.mainRecyclerView.addItemDecoration(LastItemOffsetDecoration(R.dimen.margin_medium))
        binding.mainRecyclerView.addItemDecoration(DifferentViewTypesOffsetDecoration())
        binding.mainRecyclerView.addItemDecoration(SameViewTypesOffsetDecoration())

        binding.bottomRecyclerView.adapter = bottomAdapter
        binding.bottomRecyclerView.addItemDecoration(FirstItemOffsetDecoration(R.dimen.margin_medium))
        binding.bottomRecyclerView.addItemDecoration(LastItemOffsetDecoration(R.dimen.margin_medium))
        binding.bottomRecyclerView.addItemDecoration(DifferentViewTypesOffsetDecoration())
        binding.bottomRecyclerView.addItemDecoration(SameViewTypesOffsetDecoration())
    }

    companion object {

        const val TAG = "AddServiceFragment"

        private const val ARGUMENTS = "ARGUMENTS"

        fun newInstance(arguments: FinesArguments) = FinesFragment().also {
            it.arguments = Bundle().apply {
                putParcelable(ARGUMENTS, arguments)
            }
        }

        private val FinesFragment.finesArguments: FinesArguments
            get() {
                return arguments?.getParcelable(ARGUMENTS) ?: throw IllegalArgumentException("Фрагмент запущен некорректно")
            }
    }
}