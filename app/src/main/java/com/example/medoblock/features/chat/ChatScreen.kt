package com.example.medoblock.features.chat

import android.os.Build
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.medoblock.R
import com.example.medoblock.core.utils.LoadingState
import com.example.medoblock.features.Screens
import com.example.medoblock.features.chat.components.BotMessage
import com.example.medoblock.features.chat.components.UserMessage
import com.example.medoblock.features.shared.components.MTopAppBar
import com.example.medoblock.features.shared.utils.LocalDimension
import com.example.medoblock.features.shared.utils.MDateTime
import com.example.medoblock.features.ui.theme.chatGreen
import com.example.medoblock.features.ui.theme.gray30
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

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val keyboardOffset = WindowInsets.ime.getBottom(LocalDensity.current)
    val isLoading by viewModel.responseLoading.collectAsState()

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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MTopAppBar(
                title = Screens.Chat.name,
                onBack = { navController.navigateUp() },
                scrollBehavior = scrollBehavior
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
                Spacer(modifier = Modifier.height(16.dp))

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

                LoadingMsgGif(isLoading = isLoading == LoadingState.LOADING)
                Spacer(modifier = Modifier.height(70.dp))
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
private fun LoadingMsgGif(
    modifier: Modifier = Modifier,
    isLoading: Boolean
) {
    val context = LocalContext.current

    val imageLoader = ImageLoader
        .Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    val painter = rememberAsyncImagePainter(
        ImageRequest
            .Builder(context)
            .data(data = R.drawable.cat_loading)
            .build(),
        imageLoader = imageLoader
    )

    if(isLoading){
        Image(
            modifier = modifier.size(104.dp),
            painter = painter,
            contentDescription = null
        )
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
                .height(70.dp)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = screenXPadding, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)){
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                        .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                        .border(1.dp, gray30.copy(.3f), RoundedCornerShape(6.dp))
                        .padding(start = 16.dp, end = 8.dp)
                    ,
                ){
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        BasicTextField(
                            value = inputText,
                            onValueChange = { inputText = it },
                            singleLine = true,
                            textStyle = MaterialTheme.typography.titleSmall
                        )
                    }

                    if(inputText.isEmpty()){
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.enter_query),
                                style = MaterialTheme.typography.titleSmall,
                                color = gray30,
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onSend(inputText); inputText = "" }
                    .background(chatGreen),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    modifier = Modifier.size(19.dp),
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}