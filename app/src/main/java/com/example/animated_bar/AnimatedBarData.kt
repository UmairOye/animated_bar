package com.example.animated_bar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

val shoes = listOf(
    AnimatedFeature(
        iconRes = R.drawable.one,
        title = "Pegasus 41",
        subtitle = "Responsive ride for daily runs",
        buttonText = "View Specs",
        popupElements = listOf(
            PopupElement(
                Offset(-0.52f, -0.88f),
                -10f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.one
            ),
            PopupElement(
                Offset(0.0f, -1.03f),
                0f,
                Color.White,
                88f,
                68f,
                imageRes = R.drawable.two_two
            ),
            PopupElement(
                Offset(0.52f, -0.88f),
                10f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.three_second
            )
        ),
        themeColor = Color(0xFF4285F4)
    ),
    AnimatedFeature(
        iconRes = R.drawable.two,
        title = "Vomero 17",
        subtitle = "Soft cushioning and stable feel",
        buttonText = "View Specs",
        popupElements = listOf(
            PopupElement(
                Offset(-0.52f, -0.88f),
                -10f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.two_one
            ),
            PopupElement(
                Offset(0.0f, -1.04f),
                0f,
                Color.White,
                88f,
                70f,
                imageRes = R.drawable.two_two
            ),
            PopupElement(
                Offset(0.52f, -0.88f),
                10f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.two_three
            )
        ),
        themeColor = Color(0xFFF4B400)
    ),
    AnimatedFeature(
        iconRes = R.drawable.three,
        title = "Invincible 3",
        subtitle = "High comfort with ZoomX foam",
        buttonText = "View Specs",
        popupElements = listOf(
            PopupElement(
                Offset(-0.55f, -0.85f),
                -12f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.three_first
            ),
            PopupElement(
                Offset(0.0f, -1.05f),
                0f,
                Color.White,
                88f,
                68f,
                imageRes = R.drawable.three_second
            ),
            PopupElement(
                Offset(0.55f, -0.85f),
                12f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.three_third
            )
        ),
        themeColor = Color(0xFFDB4437)
    ),
    AnimatedFeature(
        iconRes = R.drawable.four,
        title = "Structure 25",
        subtitle = "Supportive and smooth trainer",
        buttonText = "View Specs",
        popupElements = listOf(
            PopupElement(
                Offset(-0.52f, -0.88f),
                -10f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.four
            ),
            PopupElement(
                Offset(0.0f, -1.03f),
                0f,
                Color.White,
                88f,
                68f,
                imageRes = R.drawable.two_three
            ),
            PopupElement(
                Offset(0.52f, -0.88f),
                10f,
                Color.White,
                78f,
                72f,
                imageRes = R.drawable.three_first
            )
        ),
        themeColor = Color(0xFF0F9D58)
    )
)
