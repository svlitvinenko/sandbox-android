package ru.svlit.sandbox.feature.host.presentation

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import ru.svlit.sandbox.core.designsystem.item.SingleFragmentActivity


@FlowPreview
@ExperimentalCoroutinesApi
class HostActivity : SingleFragmentActivity<HostFragment>() {

    override fun createFragment(): HostFragment {
        return HostFragment.newInstance()
    }

    override fun getFragmentTag(): String {
        return HostFragment.TAG
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HostActivity::class.java)
        }
    }
}
