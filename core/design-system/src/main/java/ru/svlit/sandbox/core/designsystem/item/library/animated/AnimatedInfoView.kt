package ru.svlit.sandbox.core.designsystem.item.library.animated

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieDrawable
import ru.svlit.sandbox.core.designsystem.R
import ru.svlit.sandbox.core.designsystem.databinding.DsInternalAnimatedViewBinding
import ru.svlit.sandbox.core.models.TextWrapper

/**
 * Custom View, отображающая элемент "Анимированный онбординг".
 *
 * @author Sergei Litvinenko on 23 Feb, 2021.
 */
class AnimatedInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: DsInternalAnimatedViewBinding

    init {
        inflate(context, R.layout.ds_internal_animated_view, this)
        binding = DsInternalAnimatedViewBinding.bind(this)
    }

    fun setItem(animatedInfo: AnimatedInfo) {
        setTitle(animatedInfo.title)
        setSubtitle(animatedInfo.subtitle)
        setAnimation(animatedInfo.animation)
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

    fun setAnimation(animationWrapper: AnimationWrapper) {
        when (animationWrapper) {
            is AnimationWrapper.ByUrl -> binding.illustrationImageView.setAnimationFromUrl(animationWrapper.url)
            is AnimationWrapper.ByResource -> binding.illustrationImageView.setAnimation(animationWrapper.resource)
        }

        binding.illustrationImageView.repeatMode = LottieDrawable.RESTART
        binding.illustrationImageView.repeatCount = LottieDrawable.INFINITE
        binding.illustrationImageView.playAnimation()

    }
}