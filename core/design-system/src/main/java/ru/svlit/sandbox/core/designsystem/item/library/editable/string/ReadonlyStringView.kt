package ru.svlit.sandbox.core.designsystem.item.library.editable.string

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.TextViewCompat.setTextAppearance
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.databinding.DsInternalReadonlyStringBinding
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Custom View для отображения строки только для чтения.
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class ReadonlyStringView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: DsInternalReadonlyStringBinding

    init {
        inflate(context, R.layout.ds_internal_readonly_string, this)
        binding = DsInternalReadonlyStringBinding.bind(this)
    }

    fun setTitle(title: TextWrapper, titleStyleType: ReadonlyString.TitleStyleType) {
        val text: String = title.getText(context)
        if (text.isBlank()) {
            binding.titleTextView.text = null
            binding.titleTextView.visibility = GONE
        } else {
            binding.titleTextView.text = text
            binding.titleTextView.visibility = VISIBLE
        }
        setTitleStyle(titleStyleType)
    }

    fun setTitleStyle(titleStyleType: ReadonlyString.TitleStyleType) {
        setTextAppearance(
            binding.titleTextView, when (titleStyleType) {
                ReadonlyString.TitleStyleType.HEADLINE -> R.style.TextAppearance_Sandbox_Headline
                ReadonlyString.TitleStyleType.BODY_1 -> R.style.TextAppearance_Sandbox_Body1
                ReadonlyString.TitleStyleType.BODY_2 -> R.style.TextAppearance_Sandbox_Body2
            }
        )
    }

    fun setBody(body: TextWrapper, bodyStyleType: ReadonlyString.BodyStyleType) {
        val text: String = body.getText(context)
        if (text.isBlank()) {
            binding.bodyTextView.text = null
            binding.bodyTextView.visibility = GONE
        } else {
            binding.bodyTextView.text = body.getText(context)
            binding.bodyTextView.visibility = VISIBLE
        }
        setBodyStyle(bodyStyleType)
    }

    fun setBodyStyle(bodyStyleType: ReadonlyString.BodyStyleType) {
        setTextAppearance(
            binding.bodyTextView, when (bodyStyleType) {
                ReadonlyString.BodyStyleType.PRIMARY -> R.style.TextAppearance_Sandbox_Body2
                ReadonlyString.BodyStyleType.SECONDARY -> R.style.TextAppearance_Sandbox_Body2_Secondary
            }
        )
    }
}