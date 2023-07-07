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

@Composable
fun SellMedicine(
    modifier: Modifier = Modifier,
    data: String?,
) {
    val splitVal = data?.split(",")
    Log.d("TAG", "Sell Med: $splitVal")
    val id = splitVal?.get(0)
    val name = splitVal?.get(1)
    val desc = splitVal?.get(2)
    val manuId = splitVal?.get(3)
    val manuDate = splitVal?.get(5)?.split(":")?.get(1)
    val expDate = splitVal?.get(6)
    val fdaStatus = splitVal?.get(7)?.split(":")?.get(1)
    val fdaAdminId = splitVal?.get(8)
    val price = splitVal?.get(9)?.split(":")?.get(1)
    val count = splitVal?.get(11)?.split(":")?.get(1)
    val medSupplyChainAddr = splitVal?.get(12)

    val isActive = remember(data){ !data.isNullOrEmpty()}

    TimelineContainer(
        modifier = modifier,
        isActive = isActive,
        isEnd = true,
        isStart = false
    ) {
        Column {
            Row {
                Image(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(if (!isActive) 80.dp else 60.dp),
                    painter = painterResource(id = R.drawable.teamwork),
                    contentDescription = null
                )

                if(isActive){
                    Spacer(modifier = Modifier.padding(end = 8.dp))

                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                            text = "Medicine sold",
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
                            text = "FDA Status " + (fdaStatus ?: ""),
                            style = MaterialTheme.typography.bodySmall,
                            color = green2
                        )
                    }
                }
            }

            if(isActive){
               Column(modifier = Modifier.padding(start = 8.dp)) {
                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text =  name ?: "",
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )

                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text =  desc ?: "",
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White,
                       maxLines = 4 ,
                       overflow = TextOverflow.Ellipsis
                   )

                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text =  "Cost $" + (price ?: ""),
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )

                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text = "Count " + count?.substring(2)?.let { java.lang.Long.parseLong(it, 16)}?.toString() ?: "",
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )

                   val manuDateCov = MDateTime.timestampToDate(manuDate?.substring(2)?.let { java.lang.Long.parseLong(it, 16) * 1000})
                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text = "Created at: " + manuDateCov ,
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )

                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text = "Exp Date: ${expDate?.split(":")?.get(1)}" ,
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )

                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text = "Creator Id : "  + trimAddress(manuId?.split (":")?.get(1) ?: ""),
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )

                   Text(
                       modifier = Modifier.padding(top = 8.dp),
                       text = "FDA admin ID: "  + trimAddress(fdaAdminId?.split (":")?.get(1) ?: ""),
                       style = MaterialTheme.typography.bodySmall,
                       color = Color.White
                   )
               }
            }
        }
    }
}