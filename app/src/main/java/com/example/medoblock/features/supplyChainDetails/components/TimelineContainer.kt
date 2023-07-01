package com.example.medoblock.features.supplyChainDetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medoblock.R
import com.example.medoblock.core.components.TimelineDottedBoth
import com.example.medoblock.core.utils.Extensions.conditional
import com.example.medoblock.features.ui.theme.Pink80
import com.example.medoblock.features.ui.theme.gray1

@Composable
fun TimelineContainer(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    isEnd: Boolean,
    isStart: Boolean,
    content: @Composable (() -> Unit)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        TimelineDottedBoth(
            modifier = Modifier.heightIn(min = 130.dp),
            isStart = isStart,
            isEnd = isEnd,
            dottedColor = if(isActive) Pink80 else gray1.copy(.6f),
            circleColor = if(isActive) Color.Red else gray1.copy(.6f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .conditional(isActive,{ weight(1f)})
                .fillMaxHeight()
                .padding(top = 16.dp, bottom = 16.dp)
                .drawWithCache {
                    onDrawBehind {
                        val brush = Brush.linearGradient(
                            listOf(
                                Color(0xFF9E82F0),
                                Color(0xFF42A5F5)
                            )
                        )

                        val brushInActive = Brush.linearGradient(
                            listOf(
                                gray1.copy(.6f),
                                gray1.copy(.6f)
                            )
                        )

                        drawRoundRect(
                            if(isActive) brush else brushInActive,
                            cornerRadius = CornerRadius(10.dp.toPx())
                        )
                    }
                }
                .padding(8.dp)
        ){
            Image(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(20.dp)
                    .offset(x = (-24).dp)
                    .rotate(-90f)
                ,
                painter = painterResource(id = R.drawable.triangle),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    if(isActive) Color(0xFF9E82F0) else gray1.copy(.6f)
                )
            )

            content()
        }
    }
}