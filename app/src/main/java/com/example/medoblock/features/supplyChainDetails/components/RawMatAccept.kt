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
import com.example.medoblock.features.ui.theme.green1
import com.example.medoblock.features.ui.theme.orange1

@Composable
fun RawMatAccept(
    modifier: Modifier = Modifier,
    rawMatAccept: String?,
) {
    val splitVal = rawMatAccept?.split(",")
    Log.d("TAG", "RawMatAccept: $splitVal")
    val name = splitVal?.get(1)
    val requesterId = splitVal?.get(5)
    val requestedToId = splitVal?.get(6)
    val rawMatId = splitVal?.get(7)
    val date = splitVal?.get(4)
    val status = splitVal?.get(10)

    val isActive = remember(rawMatAccept){ !rawMatAccept.isNullOrEmpty()}

    TimelineContainer(
        modifier = modifier,
        isActive = isActive,
        isEnd = false,
        isStart = false
    ) {
        Column {
            Row {
                Image(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(if(!isActive) 80.dp else 60.dp),
                    painter = painterResource(id = R.drawable.agreement),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(end = 8.dp))

                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "Raw material accepted",
                        style = MaterialTheme.typography.bodyOMedium,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Status : "  + (status ?. split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = orange1
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