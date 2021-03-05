package ru.svlit.sandbox.feature.fines.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import ru.svlit.sandbox.core.designsystem.item.SingleFragmentActivity
import ru.svlit.sandbox.feature.fines.R

/**
 * Активити Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class FinesActivity : SingleFragmentActivity<FinesFragment>() {

    override fun createFragment(): FinesFragment {
        return FinesFragment.newInstance(arguments)
    }

    override fun getFragmentTag(): String = FinesFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_24)
    }

    private val arguments: FinesArguments
        get() = intent.getParcelableExtra(ARGUMENTS)
            ?: throw IllegalArgumentException("Активность запущена некорректно")

    companion object {

        private const val ARGUMENTS = "ARGUMENTS"

        fun newIntent(activity: Activity, arguments: FinesArguments): Intent {
            return Intent(activity, FinesActivity::class.java).apply {
                putExtra(ARGUMENTS, arguments)
            }
        }
    }
}