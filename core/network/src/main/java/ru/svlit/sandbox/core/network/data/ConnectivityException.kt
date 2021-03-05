package ru.svlit.sandbox.core.network.data

import ru.svlit.sandbox.core.network.domain.ConnectivityStatus

/**
 * Ошибка соединения.
 *
 * @property status статус соединения.
 *
 * @author Sergei Litvinenko on 13 Feb, 2021.
 */
class ConnectivityException(val status: ConnectivityStatus) : Exception("Ошибка соединения: $status.")
