package com.example.animated_bar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedBar(
    modifier: Modifier = Modifier,
    onImageClick: (Int) -> Unit = {}
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        shoes.forEachIndexed { index, shoe ->
            val isSelected = selectedIndex == index
            val isBlurred = selectedIndex != -1 && !isSelected
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            val selectionTransition = updateTransition(targetState = isSelected, label = "selectionTransition")

            val opacity by animateFloatAsState(
                targetValue = if (isBlurred) 0.5f else 1f,
                animationSpec = tween(300),
                label = "opacity"
            )

            val popAnimation by selectionTransition.animateFloat(
                transitionSpec = { tween(durationMillis = 420, easing = FastOutSlowInEasing) },
                label = "popAnimation"
            ) { selected ->
                if (selected) 1f else 0f
            }

            val detailsAlpha by selectionTransition.animateFloat(
                transitionSpec = { tween(durationMillis = 260, easing = LinearOutSlowInEasing) },
                label = "detailsAlpha"
            ) { selected ->
                if (selected) 1f else 0f
            }

            val pressScale by animateFloatAsState(
                targetValue = when {
                    isPressed -> 0.92f
                    isSelected -> 1.06f
                    else -> 1f
                },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessHigh
                ),
                label = "pressScale"
            )

            val liftY by animateFloatAsState(
                targetValue = if (isSelected) -12f else 0f,
                animationSpec = tween(durationMillis = 380, easing = FastOutSlowInEasing),
                label = "liftY"
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .graphicsLayer {
                        this.alpha = opacity
                        this.clip = false
                    }
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { selectedIndex = index },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 110.dp, bottom = 10.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        shoe.popupElements.forEachIndexed { specIndex, spec ->
                            val density = LocalDensity.current
                            val width = with(density) { spec.width.dp.toPx() }
                            val height = with(density) { spec.height.dp.toPx() }
                            val progress by animateFloatAsState(
                                targetValue = if (isSelected) 1f else 0f,
                                animationSpec = if (isSelected) {
                                    tween(
                                        durationMillis = 360,
                                        delayMillis = specIndex * 55,
                                        easing = FastOutSlowInEasing
                                    )
                                } else {
                                    tween(
                                        durationMillis = 180,
                                        easing = FastOutLinearInEasing
                                    )
                                },
                                label = "specProgress_${index}_$specIndex"
                            )

                            Box(
                                modifier = Modifier
                                    .graphicsLayer {
                                        translationX = spec.offset.x * width * progress
                                        translationY = spec.offset.y * height * progress
                                        transformOrigin = TransformOrigin(0.5f, 1f)
                                        scaleX = 0.2f + (0.8f * progress)
                                        scaleY = 0.2f + (0.8f * progress)
                                        rotationZ = spec.rotation * progress
                                        alpha = progress
                                    }
                                    .requiredSize(spec.width.dp, spec.height.dp)
                                    .then(if (spec.hasShadow) Modifier.shadow(8.dp, RoundedCornerShape(12.dp)) else Modifier)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color.White, RoundedCornerShape(12.dp))
                            ) {
                                if (spec.imageRes != null) {
                                    Image(
                                        painter = painterResource(id = spec.imageRes),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null
                                            ) {
                                                onImageClick(spec.imageRes)
                                            }
                                    )
                                } else {
                                    Box(
                                        Modifier
                                            .matchParentSize()
                                            .background(spec.color, RoundedCornerShape(12.dp))
                                    )
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White, RoundedCornerShape(12.dp))
                                .graphicsLayer {
                                    val iconScale = (1f + (0.36f * popAnimation)) * pressScale
                                    scaleX = iconScale
                                    scaleY = iconScale
                                    translationY = liftY
                                    rotationZ = if (isSelected) (index - 1.5f) * 0.9f else 0f
                                }
                        ) {
                            Image(
                                painter = painterResource(id = shoe.iconRes),
                                contentDescription = shoe.title,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(2.dp)
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = 0.65f,
                                    stiffness = 250f
                                )
                            )
                    ) {
                        AnimatedVisibility(
                            visible = isSelected,
                            enter = expandVertically(
                                animationSpec = tween(280, easing = FastOutSlowInEasing),
                                expandFrom = Alignment.Top
                            ),
                            exit = shrinkVertically(
                                animationSpec = tween(220, easing = FastOutLinearInEasing),
                                shrinkTowards = Alignment.Top
                            )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(top = 6.dp, bottom = 4.dp)
                                    .graphicsLayer { alpha = detailsAlpha }
                            ) {
                                Text(
                                    text = shoe.title,
                                    fontSize = 18.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = shoe.subtitle,
                                    fontSize = 10.sp,
                                    lineHeight = 12.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.DarkGray,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    val pillColor by animateColorAsState(
                        targetValue = if (isSelected) shoe.themeColor.copy(alpha = 0.15f) else Color(0xFFEEEEEE),
                        animationSpec = tween(400),
                        label = "pillColor"
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(pillColor, RoundedCornerShape(20.dp))
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedContent(
                            targetState = isSelected,
                            transitionSpec = {
                                scaleIn(animationSpec = tween(220, easing = FastOutSlowInEasing)) togetherWith
                                        scaleOut(animationSpec = tween(160, easing = FastOutLinearInEasing))
                            },
                            label = "pillText"
                        ) { targetIsSelected ->
                            Text(
                                text = if (targetIsSelected) shoe.buttonText else shoe.title,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (targetIsSelected) shoe.themeColor else Color(0xFF666666)
                            )
                        }
                    }
                }
            }
        }
    }
}

