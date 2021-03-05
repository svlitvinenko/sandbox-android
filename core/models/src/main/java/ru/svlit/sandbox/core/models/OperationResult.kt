package ru.svlit.sandbox.core.models

/**
 * Базовая модель результата операции.
 *
 * @author Sergei Litvinenko on 16 Feb, 2021.
 */
class OperationResult<S : Any, F : Any> private constructor(
    private val success: S? = null,
    private val failure: F? = null
) {

    val isSuccessful: Boolean = success != null

    /**
     * Преобразует результат в экземпляр типа [R]. Если результат успешный, вызовется [onSuccess], иначе
     * вызовется [onFailure].
     */
    fun <R : Any> map(onSuccess: (S) -> R, onFailure: (F) -> R) {
        when {
            success != null -> onSuccess.invoke(success)
            failure != null -> onFailure.invoke(failure)
            else -> throw IllegalStateException("Невозможно присутствие в ответе и успеха, и неудачи одновременно.")
        }
    }

    /**
     * Получает результат для дальнейшей обработки. Если результат успешный, вызовется [onSuccess], иначе
     * вызовется [onFailure].
     */
    fun get(onSuccess: (S) -> Unit, onFailure: (F) -> Unit) = map(onSuccess, onFailure)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OperationResult<*, *>

        if (success != other.success) return false
        if (failure != other.failure) return false

        return true
    }

    override fun hashCode(): Int {
        var result = success?.hashCode() ?: 0
        result = 31 * result + (failure?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return if (success != null) {
            "Result(success=$success)"
        } else {
            "Result(failure=$failure)"
        }
    }

    companion object {
        /**
         * Создаёт успешный результат со значением [value].
         */
        fun <S : Any, F : Any> success(value: S): OperationResult<S, F> = OperationResult(success = value)

        fun <F : Any> success(): OperationResult<Unit, F> = success(Unit)

        /**
         * Создаёт неуспешный результат со значением [failure].
         */
        fun <S : Any, F : Any> failure(failure: F): OperationResult<S, F> = OperationResult(failure = failure)
    }
}