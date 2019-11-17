package ru.stowawaydev.template.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * template header (replace it)
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
