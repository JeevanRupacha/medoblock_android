package com.example.medoblock.features.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.medoblock.features.ui.theme.MedoBlockTheme
import com.example.medoblock.features.ui.theme.dividerColor
import com.example.medoblock.features.ui.theme.dividerColor2

object MDivider {
    @Composable
    fun TitleBarDivider(
        modifier: Modifier = Modifier,
        thickness: Dp = 4.dp,
        parentOverflowPadding: Dp = 0.dp,
        color: Color = dividerColor2
    ) {
        Divider(
            modifier = modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(
                            maxWidth = constraints.maxWidth + parentOverflowPadding.roundToPx() + 16.dp.roundToPx()
                        )
                    )
                    layout(placeable.width, placeable.height){
                        placeable.place(0,0)
                    }
                }
            ,
            thickness = thickness,
            color = color
        )
    }

    @Composable
    fun DividerLarge(
        modifier: Modifier = Modifier,
        thickness: Dp = 4.dp,
        color: Color = dividerColor
    ) {
        Divider(modifier= modifier, thickness = thickness, color = color)
    }

    @Composable
    fun DividerExtraLarge(
        modifier: Modifier = Modifier,
        thickness: Dp = 8.dp,
        color: Color = dividerColor
    ) {
        Divider(modifier= modifier, thickness = thickness, color = color)
    }

    @Composable
    fun DividerMedium(
        modifier: Modifier = Modifier,
        thickness: Dp = 2.dp,
        color: Color = dividerColor
    ) {
        Divider(modifier= modifier, thickness = thickness, color = color)
    }

    @Composable
    fun DividerSmallLight(
        modifier: Modifier = Modifier,
        thickness: Dp = 1.4.dp,
        color: Color = dividerColor
    ) {
        Divider(modifier= modifier.alpha(.4f), thickness = thickness, color = color)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDivider() {
    MedoBlockTheme {
        Column(modifier = Modifier.padding(8.dp)){
            MDivider.DividerExtraLarge()
            Spacer(Modifier.height(16.dp))
            MDivider.TitleBarDivider()
            Spacer(Modifier.height(16.dp))
            MDivider.DividerLarge()
            Spacer(Modifier.height(16.dp))
            MDivider.DividerMedium()
            Spacer(Modifier.height(16.dp))
            MDivider.DividerSmallLight()
            Spacer(Modifier.height(16.dp))
        }
    }
}