package ru.svlit.sandbox.core.base.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Поставщик моделей представления с безопасным способом отложенной инициализации.
 *
 * @constructor создаёт фабрику.
 * @param supplier поставщик экземпляра модели представления.
 * @param onCreateHook инициализирующая функция. Вызывается сразу после создания модели представления.
 *
 * @author Sergei Litvinenko on 14 Feb, 2021.
 */
class ViewModelProviderFactory<V : ViewModel>(
    private val supplier: () -> V,
    private val onCreateHook: (V) -> Unit = {}
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel: V = supplier.invoke()
        onCreateHook.invoke(viewModel)
        return viewModel as T
    }
}
