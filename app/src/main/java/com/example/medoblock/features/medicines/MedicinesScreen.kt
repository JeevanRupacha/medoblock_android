package com.example.medoblock.features.medicines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medoblock.core.utils.LoadingState
import com.example.medoblock.domain.models.Medicine
import com.example.medoblock.features.Screens
import com.example.medoblock.features.shared.components.MTopAppBar
import com.example.medoblock.features.shared.utils.LocalDimension
import com.example.medoblock.features.ui.theme.bodyOMedium
import com.example.medoblock.features.ui.theme.bodyOSmall
import com.example.medoblock.features.ui.theme.green1
import com.example.medoblock.features.ui.theme.orange1
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicinesScreen(
    navController: NavController,
    viewModel: MedicineVM = hiltViewModel()
) {
    val dimension = LocalDimension.current
    val containerXPadding = dimension.ScreenHorizontalPadding

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            MTopAppBar(
                title = Screens.Medicines.name,
                onBack = { navController.navigateUp() }
            )
        },
    ) { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(horizontal = containerXPadding)) {
                Spacer(modifier = Modifier.height(topPadding))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
                ){
                    items(items = state.medicines, key = { it.id!! }){
                        MedicineCard(medicine = it)
                    }
                }
            }

            if(state.isLoading == LoadingState.LOADING){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun MedicineCard(
    modifier: Modifier = Modifier,
    medicine: Medicine
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 300.dp)
            .padding(bottom = 20.dp)
        ,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            val id = medicine.id
            val trim = id?.substring(0, 6) + "..." + id?.substring(id.length - 6, id.length)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = medicine.name ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f)
                )

                Text(
                    text = trim,
                    style = MaterialTheme.typography.bodyOMedium
                )
            }

            Text(
                text = medicine.description ?: "",
                style = MaterialTheme.typography.bodyOMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(.7f))
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Created at",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W300
                    )

                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                    val date = medicine.manuDate?.toLong()?.let { Date(it * 1000) }
                    val dateStr = date?.let { dateFormat.format(it) }
                    Text(
                        text = dateStr ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer.copy(.7f))
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Expired at",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W300
                    )
                    Text(
                        text = medicine.expDate ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer.copy(.6f))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "FDA",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W300
                    )
                    Text(
                        text = medicine.fdaStatus ?: "",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 6.dp),
                        color = green1
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Price",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W300
                    )
                    Text(
                        text = "$${medicine.price}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Count",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.W300
                    )
                    Text(
                        text = "${medicine.count} unit",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 6.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))
            Row {
                val medStr = medicine.medSupplyChainAddr
                val trimAddr = medStr?.substring(0, 6) + "..." + medStr?.substring(medStr.length - 6, medStr.length)

                Text(text = "Address: ", style = MaterialTheme.typography.bodyOMedium)
                Text(
                    text = trimAddr,
                    style = MaterialTheme.typography.bodyOMedium,
                    color = orange1
                )
            }
        }
    }
}