package com.example.medoblock.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medoblock.features.ui.theme.gray1

@Composable
fun TimelineDottedBoth(
    modifier: Modifier = Modifier,
    isStart: Boolean,
    isEnd: Boolean,
    dottedColor: Color = gray1,
    circleColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier.fillMaxHeight().width(40.dp)
    ) {
        if(!isStart){
            Box(
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .align(Alignment.TopCenter)
                    .padding(bottom = 16.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                DottedLine(
                    vertical = true,
                    arcStrokeColor = dottedColor
                )
            }
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        ) {
            DotCircleArcCanvas(
                arcStrokeColor = dottedColor,
                circleColor = circleColor
            )
        }

        if(!isEnd){
            Box(
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .align(Alignment.BottomCenter)
                    .padding(top = 16.dp)
                ,
                contentAlignment = Alignment.Center
            ) {
                DottedLine(
                    arcStrokeColor = dottedColor,
                    vertical = true
                )
            }
        }
    }
}