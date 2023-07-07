package com.example.medoblock.features.chat.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medoblock.R
import com.example.medoblock.domain.models.Message
import com.example.medoblock.features.shared.utils.MDateTime
import com.example.medoblock.features.ui.theme.MedoBlockTheme
import com.example.medoblock.features.ui.theme.bodyOSmall

@Composable
fun BotMessage(
    modifier: Modifier = Modifier,
    message: Message,
    isGrouped: Boolean = false,
    showTime: Boolean = true
) {
    var visible by remember{ mutableStateOf(false) }

    LaunchedEffect(key1 = Unit){
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(600))
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Row(modifier = Modifier.fillMaxWidth(.9f)){
                if(!isGrouped){
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.chatbot),
                        contentDescription = null ,
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Box(modifier = Modifier
                        .size(32.dp)
                        .background(Color.Transparent))
                }

                Spacer(modifier = Modifier.padding(start = 16.dp))

                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 20.dp,
                                    bottomEnd = 20.dp,
                                    bottomStart = 20.dp,
                                    topStart = if (!isGrouped) 0.dp else 20.dp
                                )
                            )
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(12.dp)
                    ){
                        Text(
                            text = message.message ?: "",
                            style = MaterialTheme.typography.bodyOSmall
                        )
                    }

                    val time = MDateTime.timestampToTime(message.timeStamp)
                    if(showTime){
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(top = 4.dp),
                            text = time,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBotMessage() {
    MedoBlockTheme {
        BotMessage(message = Message(
            "How can I help ?",
            1687368328,
            true
        ))
    }
}