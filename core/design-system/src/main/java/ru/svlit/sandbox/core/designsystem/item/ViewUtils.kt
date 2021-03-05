@file:JvmName("ViewUtils")

package ru.svlit.sandbox.core.designsystem.item

import android.view.View
import android.view.ViewGroup


fun View.setEnabledRecursively(isEnabled: Boolean) {
    if (this is ViewGroup) {
        for (i: Int in 0 until childCount) {
            val child: View = getChildAt(i)
            child.isEnabled = isEnabled
            if (child is ViewGroup) {
                child.setEnabledRecursively(isEnabled)
            }
        }
    } else {
        setEnabled(isEnabled)
    }
}