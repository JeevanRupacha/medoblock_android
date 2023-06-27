package com.example.medoblock.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medoblock.features.ui.theme.gray1

@Composable
fun TimelineDottedBoth(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .height(20.dp)
                .align(Alignment.TopCenter)
                .padding(end = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            DottedLine(
                arcStrokeColor = gray1,
                vertical = true
            )
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        ) {
            DotCircleArcCanvas(
                arcStrokeColor = gray1,
                circleColor = MaterialTheme.colorScheme.primary
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .height(20.dp)
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            DottedLine(
                arcStrokeColor = gray1,
                vertical = true
            )
        }
    }
}