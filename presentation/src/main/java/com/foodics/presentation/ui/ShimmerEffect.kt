package com.foodics.presentation.ui

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import utils.sdp


@Composable
fun ShimmerEffectView(
) {
    Column(Modifier.fillMaxWidth()) {
        ShimmerBox(height = 34.sdp, fillMaxWidth = true )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(6) {
                ShimmerBox(height = 100.sdp, fillMaxWidth = true)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            ShimmerBox(width = 100.sdp, height = 25.sdp)
        }
        ShimmerBox(width = 150.sdp, height = 25.sdp)

        ShimmerBox(height = 250.sdp, fillMaxWidth = true)

    }
}

private fun Modifier.shimmerEffect(
    toShow: Boolean = true,
    customWidth: Dp = 0.dp,
    customHeight: Dp = 0.dp,
    fillMaxWidth: Boolean = false,
    fillMaxHeight: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(12.dp)
): Modifier = composed {
    if (toShow) {
        var size by remember { mutableStateOf(IntSize.Zero) }
        val transition = rememberInfiniteTransition(label = "")
        val startOffsetX by transition.animateFloat(
            initialValue = -2 * size.width.toFloat(),
            targetValue = 2 * size.width.toFloat(),
            animationSpec = infiniteRepeatable(
                animation = tween(1000)
            ), label = ""
        )
        background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xCDE4E3E3),
                    Color(0xCBCECBCB),
                    Color(0xCDE4E3E3),
                ),
                start = Offset(startOffsetX, 0f),
                end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
            ),
            shape = shape
        )
            .onGloballyPositioned {
                size = it.size
            }
            .alpha(0f)
            .then(if (customWidth != 0.sdp) then(Modifier.width(customWidth)) else Modifier)
            .then(if (customHeight != 0.sdp) then(Modifier.height(customHeight)) else Modifier)
            .then(if (fillMaxWidth) then(Modifier.fillMaxWidth()) else Modifier)
            .then(if (fillMaxHeight) then(Modifier.fillMaxHeight()) else Modifier)
    } else {
        Modifier
    }
}


@Composable
private fun ShimmerBox(
    width: Dp? = null,
    height: Dp,
    fillMaxWidth: Boolean = false,
    paddingTop: Dp = 16.dp,
    paddingStart: Dp = 16.dp,
    paddingEnd: Dp = 16.dp
) {
    Box(
        modifier = Modifier
            .padding(top = paddingTop, start = paddingStart, end = paddingEnd)
            .height(height)
            .then(
                if (fillMaxWidth) Modifier.fillMaxWidth() else Modifier.width(
                    width ?: Dp.Unspecified
                )
            )
            .shimmerEffect()
    )
}