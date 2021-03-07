package ru.svlit.sandbox.feature.weather.presentation

import android.app.Activity
import android.content.Intent
import ru.svlit.sandbox.core.designsystem.item.SingleFragmentActivity
import ru.svlit.sandbox.feature.weather.models.presentation.CurrentWeatherArguments

/**
 * Активити продукта "Прогноз погоды".
 *
 * @author Sergei Litvinenko on 07 Mar, 2021.
 */
class ForecastActivity : SingleFragmentActivity<ForecastFragment>() {

    override fun createFragment(): ForecastFragment = ForecastFragment.newInstance(CurrentWeatherArguments)

    override fun getFragmentTag(): String = ForecastFragment.TAG

    companion object {

        fun newIntent(activity: Activity): Intent {
            return Intent(activity, ForecastActivity::class.java)
        }
    }
}