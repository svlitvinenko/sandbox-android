package ru.svlit.sandbox.core.network.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Response
import ru.svlit.sandbox.core.network.domain.ConnectivityStatus

/**
 * Интерцептор, проверяющий соединение.
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val context = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline()) {
            throw ConnectivityException(ConnectivityStatus.NOT_CONNECTED)
        }

        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo = connectivityManager.activeNetworkInfo ?: return false
        return activeNetworkInfo.isConnected
    }
}