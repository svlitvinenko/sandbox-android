package ru.svlit.sandbox.core.network.data

import okhttp3.Interceptor

/**
 * Интерцептор, проверяющий соединение.
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
interface ConnectivityInterceptor : Interceptor