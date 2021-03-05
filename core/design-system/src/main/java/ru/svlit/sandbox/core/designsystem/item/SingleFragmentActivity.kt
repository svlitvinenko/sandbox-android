package ru.svlit.sandbox.core.designsystem.item

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.svlit.sandbox.core.designsystem.databinding.FragmentContainerBinding
import ru.svlit.sandbox.core.designsystem.item.adapter.Back
import ru.svlit.sandbox.core.designsystem.item.adapter.Event
import ru.svlit.sandbox.core.designsystem.item.adapter.EventListener

/**
 * [AppCompatActivity] с единственным фрагментом.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
abstract class SingleFragmentActivity<F> : AppCompatActivity(), EventListener where F : Fragment, F : EventListener {

    private lateinit var binding: FragmentContainerBinding

    abstract fun createFragment(): F

    abstract fun getFragmentTag(): String

    private val eventListenerFragment: EventListener
        get() = supportFragmentManager.fragments.filterIsInstance<EventListener>().first()

    override fun onBackPressed() {
        eventListenerFragment.onEvent(Back)
    }

    final override fun onEvent(event: Event) {
        eventListenerFragment.onEvent(event)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            binding = FragmentContainerBinding.inflate(layoutInflater)
            setContentView(binding.root)
            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, createFragment(), getFragmentTag())
                .commit()
        }
    }
}