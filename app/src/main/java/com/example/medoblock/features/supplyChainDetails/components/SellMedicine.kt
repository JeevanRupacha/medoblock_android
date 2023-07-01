package com.example.medoblock.features.supplyChainDetails.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.medoblock.R

@Composable
fun SellMedicine(
    modifier: Modifier = Modifier,
    data: String?,
) {
    val isActive = remember{ !data.isNullOrEmpty()}

    TimelineContainer(
        modifier = modifier,
        isActive = isActive,
        isEnd = false,
        isStart = true
    ) {
        Column {
            Image(
                modifier = Modifier.size(80.dp),
                painter = painterResource(id = R.drawable.drugs),
                contentDescription = null
            )
        }
    }
}