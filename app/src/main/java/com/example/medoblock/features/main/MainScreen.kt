package com.example.medoblock.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medoblock.R
import com.example.medoblock.features.shared.utils.LocalDimension
import com.example.medoblock.features.ui.theme.LightGreen10
import com.example.medoblock.features.ui.theme.gray1
import com.example.medoblock.goToChat
import com.example.medoblock.goToMedicines
import com.example.medoblock.goToSupplyChainDetails

private const val  CARD_HEIGHT = 265

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val dimension = LocalDimension.current
    val containerXPadding = dimension.ScreenHorizontalPadding

    Scaffold { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()

        Box(modifier = Modifier.fillMaxSize()){

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(52.dp)
                    .clip(RoundedCornerShape(bottomEnd = 24.dp))
                    .background(LightGreen10)
                )

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    MedicinesCard(
                        onClickCard = { navController.goToMedicines() }
                    )

                    QRScanCard(
                        onClickCard = { navController.goToSupplyChainDetails() }
                    )
                }
            }


            FloatingActionButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 32.dp, end = 32.dp),
                onClick = { navController.goToChat() }
            ) {
                Box{
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.chatbot),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun MedicinesCard(
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 24.dp))
            .background(LightGreen10)
            .padding(top = 42.dp, bottom = 12.dp, start = 12.dp, end = 42.dp)
    ) {
        ItemCard(
            modifier = Modifier
                .height(CARD_HEIGHT.dp)
                .padding(8.dp)
            ,
            label = "Medicines",
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontSize = 19.sp
            ),
            iconId = R.drawable.medicine
        ) { onClickCard() }
    }
}

@Composable
private fun QRScanCard(
    modifier: Modifier = Modifier,
    onClickCard: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
            .background(LightGreen10)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 42.dp)
        ) {
            ItemCard(
                modifier = Modifier
                    .height(CARD_HEIGHT.dp)
                    .padding(8.dp)
                ,
                label = "QR Scan",
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 19.sp
                ),
                iconId = R.drawable.qr
            ) { onClickCard() }
        }
    }
}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    label: String = "",
    iconId: Int,
    cornerRadius: Dp = 24.dp,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(1.dp, shape = RoundedCornerShape(cornerRadius), spotColor = Color.Black)
            .border(
                .7.dp,
                color = gray1.copy(alpha = .3f),
                shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.background)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = iconId),
            contentDescription = "Image",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = label,
            style = textStyle
        )
    }
}
