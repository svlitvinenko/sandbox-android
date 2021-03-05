package ru.svlit.sandbox.core.designsystem.item.library.animated

import androidx.annotation.RawRes

sealed class AnimationWrapper {

    data class ByUrl(val url: String) : AnimationWrapper()

    data class ByResource(@RawRes val resource: Int) : AnimationWrapper()
}
