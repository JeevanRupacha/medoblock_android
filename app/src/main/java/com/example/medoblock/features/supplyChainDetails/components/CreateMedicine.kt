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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.medoblock.R
import com.example.medoblock.features.shared.utils.MDateTime
import com.example.medoblock.features.shared.utils.trimAddress
import com.example.medoblock.features.ui.theme.bodyOMedium
import com.example.medoblock.features.ui.theme.green2
import com.example.medoblock.features.ui.theme.orange1
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Composable
fun CreateMedicine(
    modifier: Modifier = Modifier,
    data: String?,
) {
    val splitVal = data?.split(",")
    Log.d("TAG", "TransReqAccepted: $splitVal")
    val id = splitVal?.get(0)
    val name = splitVal?.get(1)?.split(":")?.get(1)
    val desc = splitVal?.get(2)
    val manuId = splitVal?.get(3)
    val manuDate = splitVal?.get(4)?.split(":")?.get(1)
    val expDate = splitVal?.get(5)?.split(":")?.get(1)
    val fdaStatus = splitVal?.get(6)?.split(":")?.get(1)
    val adminId = splitVal?.get(7)
    val price = splitVal?.get(8)?.split(":")?.get(1)
    val count = splitVal?.get(9)?.split(":")?.get(1)
    val medSupplyChain = splitVal?.get(10)?.split(":")?.get(1)

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
                    painter = painterResource(id = R.drawable.drugs),
                    contentDescription = null
                )

                if(isActive){
                    Spacer(modifier = Modifier.padding(end = 8.dp))

                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                            text = "Medicine created",
                            style = MaterialTheme.typography.bodyOMedium,
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onPrimary
                        )

                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = name?: "",
                            style = MaterialTheme.typography.bodyOMedium,
                            color = Color.White,
                            fontWeight = FontWeight.W600
                        )

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = trimAddress(id ?: ""),
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(.6f)
                        )

                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            text ="FDA $fdaStatus",
                            style = MaterialTheme.typography.bodySmall,
                            color = green2
                        )
                    }
                }
            }

            if(isActive){
                Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text ="Created at: ${MDateTime.timestampToDate(manuDate?.toLong())}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text ="Expiry at: $expDate",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text ="By: ${trimAddress(manuId ?: "")}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text ="Cost: $$price",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text ="Count: $count unit",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = desc ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}