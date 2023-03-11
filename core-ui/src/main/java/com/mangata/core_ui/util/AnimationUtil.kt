package com.mangata.core_ui.util

import androidx.compose.animation.core.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.debugInspectorInfo

fun Modifier.shake(
    rotationInitialValue: Float = -5f,
    rotationTargetValue: Float = 5f,
    rotationDuration: Int = 400,
    scaleInitialValue: Float = 1f,
    scaleTargetValue: Float = .85f,
    scaleDuration: Int = 400,
    easing: Easing = EaseInOut
) = composed(
    factory = {
        val infiniteTransition = rememberInfiniteTransition()
        val rotation by infiniteTransition.animateFloat(
            initialValue = rotationInitialValue,
            targetValue = rotationTargetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(rotationDuration, easing = easing),
                repeatMode = RepeatMode.Reverse
            )
        )
        val scale by infiniteTransition.animateFloat(
            initialValue = scaleInitialValue,
            targetValue = scaleTargetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(scaleDuration, easing = easing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Modifier.graphicsLayer {
            scaleX = scale
            scaleY = scale
            rotationZ = rotation
        }
    },
    inspectorInfo = debugInspectorInfo {
        name = "shake"
    }
)