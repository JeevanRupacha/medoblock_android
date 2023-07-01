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
import com.example.medoblock.features.shared.utils.MDateTime
import com.example.medoblock.features.shared.utils.trimAddress
import com.example.medoblock.features.ui.theme.bodyOMedium
import com.example.medoblock.features.ui.theme.orange1

@Composable
fun TransportInit(
    modifier: Modifier = Modifier,
    data: String?,
) {
    val splitVal = data?.split(",")
    Log.d("TAG", "TransReqAccepted: $splitVal")
    val id = splitVal?.get(0)
    val requesterId = splitVal?.get(7)
    val requestedToId = splitVal?.get(8)
    val rawMatId = splitVal?.get(7)
    val fromLoc = splitVal?.get(11)
    val toLoc = splitVal?.get(12)
    val date = MDateTime.timestampToDate(splitVal?.get(1)?.split(":")?.get(1)?.toLong())
    val status = splitVal?.get(9)
    val cost = splitVal?.get(10)

    val isActive = remember(data){ !data.isNullOrEmpty()}

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
                        .size(if (!isActive) 80.dp else 60.dp),
                    painter = painterResource(id = R.drawable.rocket),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.padding(end = 8.dp))

                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(
                        text = "Transport Initialized",
                        style = MaterialTheme.typography.bodyOMedium,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = trimAddress(id?.split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = orange1
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = (status ?. split (":")?.get(1) ?: ""),
                        style = MaterialTheme.typography.bodySmall,
                        color = orange1
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text =  "from Location: ${fromLoc?.split(":")?.get(1)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    val tol = toLoc?.split(":")
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text =  "to Location: ${if((tol?.size ?: 0) > 1) tol?.get(1) else ""}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text =  cost ?: "",
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