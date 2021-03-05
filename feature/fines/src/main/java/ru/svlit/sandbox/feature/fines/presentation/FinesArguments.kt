package ru.svlit.sandbox.feature.fines.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Аргументы для запуска экрана Штрафов.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
@Parcelize
data class FinesArguments(
    val title: TextWrapper
) : Parcelable