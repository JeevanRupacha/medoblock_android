package com.example.medoblock.features.chat

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.medoblock.features.Screens
import com.example.medoblock.features.chat.components.BotMessage
import com.example.medoblock.features.chat.components.UserMessage
import com.example.medoblock.features.shared.components.MTopAppBar
import com.example.medoblock.features.shared.utils.LocalDimension
import com.example.medoblock.features.shared.utils.MDateTime
import com.example.medoblock.R
import com.example.medoblock.features.ui.theme.bodyOLarge
import com.example.medoblock.features.ui.theme.gray30
import com.google.accompanist.insets.LocalWindowInsets
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavController,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val dimension = LocalDimension.current
    val containerXPadding = dimension.ScreenHorizontalPadding
    val containerTopPadding = dimension.ScreenTopPaddingSm
    val scrollState = rememberScrollState()
    val messages = viewModel.messages

    val keyboardOffset = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = Unit){
        delay(500)
        scrollState.animateScrollTo(
            Int.MAX_VALUE,
            animationSpec = tween(
                durationMillis = 900,
                easing = FastOutSlowInEasing
            )
        )
    }

    LaunchedEffect(key1 = messages){
        scrollState.animateScrollTo(
            Int.MAX_VALUE,
            animationSpec = tween(
                durationMillis = 900
            )
        )
    }

    Scaffold(
        topBar = {
            MTopAppBar(
                title = Screens.Chat.name,
                onBack = { navController.navigateUp() }
            )
        },
    ) { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
                    .padding(horizontal = containerXPadding)
            ) {
                Spacer(modifier = Modifier.height(topPadding + containerTopPadding))

                messages.forEachIndexed { index, message ->
                    if(message.isChatBot){
                        val date = MDateTime.timestampToDate(message.timeStamp)
                        val time = MDateTime.timestampToTime(message.timeStamp)

                        val prevMessage = if(index >= 1) messages[index - 1] else null
                        val nextMessage = if(index < messages.size -1) messages[index + 1] else null
                        val nextDate = MDateTime.timestampToDate(nextMessage?.timeStamp)
                        val nextTime = MDateTime.timestampToTime(nextMessage?.timeStamp)

                        val isGrouped = prevMessage?.isChatBot == true
                        val showTime = date != nextDate || time != nextTime || nextMessage?.isChatBot == false
                        BotMessage(message = message, isGrouped = isGrouped, showTime = showTime)
                        Spacer(modifier = Modifier.height(4.dp))
                    }

                    if(!message.isChatBot){
                        val date = MDateTime.timestampToDate(message.timeStamp)
                        val time = MDateTime.timestampToTime(message.timeStamp)

                        val prevMessage = if(index >= 1) messages[index - 1] else null
                        val nextMessage = if(index < messages.size -1) messages[index + 1] else null
                        val nextDate = MDateTime.timestampToDate(nextMessage?.timeStamp)
                        val nextTime = MDateTime.timestampToTime(nextMessage?.timeStamp)

                        val isGrouped = prevMessage?.isChatBot == false
                        val showTime = date != nextDate || time != nextTime || nextMessage?.isChatBot == true
                        UserMessage(message = message, isGrouped = isGrouped, showTime = showTime)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
                Spacer(modifier = Modifier.height(56.dp))
            }

            val dpKeyboardOffset = with(LocalDensity.current) { keyboardOffset.toDp() }

            ChatInput(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = -dpKeyboardOffset),
                onSend = {
                    if(it.isNotEmpty()) viewModel.addUserMessage(it)
                }
            )
        }
    }
}

@Composable
fun ChatInput(
    modifier: Modifier = Modifier,
    onSend : ( String ) -> Unit
) {
    val dimension = LocalDimension.current
    val screenXPadding = dimension.ScreenHorizontalPadding
    var inputText by rememberSaveable{ mutableStateOf("") }

    Card(
        modifier= modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = screenXPadding, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)){
                BasicTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleMedium
                )
                if(inputText.isEmpty()){
                    Text(
                        text = stringResource(id = R.string.enter_query),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W500,
                        color = gray30
                    )
                }
            }

            Text(
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onSend(inputText)
                            inputText = ""
                        }
                    )
                    .padding(start = 8.dp)
                ,
                text = stringResource(id = R.string.send),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.W600
            )
        }
    }
}