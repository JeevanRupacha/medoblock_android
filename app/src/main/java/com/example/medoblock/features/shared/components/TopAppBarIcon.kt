package com.example.medoblock.features.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarIcon(
    modifier: Modifier = Modifier,
    iconId: Int,
    iconColor: Color? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center
    ){
        if(iconColor != null){
            Icon(
                modifier = modifier.size(22.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
                tint = iconColor
            )
        }else{
            Icon(
                modifier = modifier.size(22.dp),
                painter = painterResource(id = iconId),
                contentDescription = null,
            )
        }
    }
}