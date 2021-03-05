package ru.svlit.sandbox.core.designsystem.item.library.info

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.databinding.DsInternalInfoViewBinding
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Custom View, отображающая информационный блок.
 *
 * @author Sergei Litvinenko on 22 Feb, 2021.
 */
class InfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: DsInternalInfoViewBinding

    init {
        inflate(getContext(), R.layout.ds_internal_info_view, this)
        binding = DsInternalInfoViewBinding.bind(this)
    }

    fun setTitle(title: TextWrapper) {
        val value = title.getText(context)
        binding.titleTextView.text = value
        binding.titleTextView.visibility = (if (value.isBlank()) GONE else VISIBLE)
    }

    fun setSubtitle(subtitle: TextWrapper) {
        val value = subtitle.getText(context)
        binding.subtitleTextView.text = value
        binding.subtitleTextView.visibility = (if (value.isBlank()) GONE else VISIBLE)
    }
}