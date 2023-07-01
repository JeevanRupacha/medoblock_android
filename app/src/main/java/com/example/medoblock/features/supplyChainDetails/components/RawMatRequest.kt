package com.example.medoblock.features.supplyChainDetails.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.medoblock.R
import com.example.medoblock.features.shared.utils.trimAddress
import com.example.medoblock.features.ui.theme.bodyOMedium
import com.example.medoblock.features.ui.theme.grayTextColor

@Composable
fun RawMatRequest(
    modifier: Modifier = Modifier,
    rawMatRequest: String?,
) {
    val splitVal = rawMatRequest?.split(",")
    val name = splitVal?.get(1)
    val requesterId = splitVal?.get(4)
    val requestedToId = splitVal?.get(5)
    val rawMatId = splitVal?.get(6)
    val count = splitVal?.get(2)
    val date = splitVal?.get(3)

    val isActive = remember(rawMatRequest){ !rawMatRequest.isNullOrEmpty()}

    TimelineContainer(
        modifier = modifier,
        isActive = isActive,
        isEnd = false,
        isStart = true
    ) {
        Column {
            Row {
                Image(
                    modifier = Modifier.size(if(!isActive) 80.dp else 60.dp),
                    painter = painterResource(id = R.drawable.processing),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(end = 8.dp))

                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "Raw material request",
                        style = MaterialTheme.typography.bodyOMedium,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = name ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = date ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "count : "  + (count ?. split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Id : "  + trimAddress(rawMatId?.split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "from : "  + trimAddress(requesterId?.split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "to : "  + trimAddress(requestedToId?.split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
        }
    }
}