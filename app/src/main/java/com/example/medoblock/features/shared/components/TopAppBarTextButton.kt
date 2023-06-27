package com.example.medoblock.features.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TopAppBarTextButton(
    modifier: Modifier = Modifier,
    cornerRadius: Shape = RoundedCornerShape(8.dp),
    buttonText: String,
    isLoading: Boolean = false,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .clip(cornerRadius)
            .clickable(!isLoading) { onClick() }
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center,
    ){
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = contentColor,
                strokeWidth = 2.dp
            )
        }else{
            Text(
                text = buttonText,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.W600,
                color = contentColor
            )
        }
    }
}