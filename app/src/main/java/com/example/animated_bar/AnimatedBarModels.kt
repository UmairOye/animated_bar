package com.example.animated_bar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class PopupElement(
    val offset: Offset,
    val rotation: Float,
    val color: Color,
    val width: Float = 70f,
    val height: Float = 80f,
    val hasShadow: Boolean = true,
    val imageRes: Int? = null
)

data class AnimatedFeature(
    val iconRes: Int,
    val title: String,
    val subtitle: String,
    val buttonText: String,
    val popupElements: List<PopupElement>,
    val themeColor: Color
)
