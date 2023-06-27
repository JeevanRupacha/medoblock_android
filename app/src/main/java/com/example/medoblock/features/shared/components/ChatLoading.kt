package com.example.medoblock.features.shared.components

import android.graphics.drawable.Animatable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    circleColor: Color = MaterialTheme.colorScheme.secondary,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
) {
//    val circles by remember{ mutableStateOf<List<Animatable>>(
//        listOf( Animatable(initialValue = 0f) , Animatable(initialValue = 0f) ,
//            Animatable(initialValue = 0f)
//        )
//    ) }

//    val circles = listOf(
//       Animatable() ,
//       Animatable(initialValue = 0f) ,
//        Animatable(initialValue = 0f)
//    )
//
//    circles.forEachIndexed { index, animatable ->
//        LaunchedEffect(key1 = animatable) {
//            delay(index * 100L)
//            animatable.animateTo(
//                targetValue = 1f,
//                animationSpec = infiniteRepeatable(
//                    animation = keyframes {
//                        durationMillis = 1200
//                        0.0f at 0 with LinearOutSlowInEasing
//                        1.0f at 300 with LinearOutSlowInEasing
//                        0.0f at 600 with LinearOutSlowInEasing
//                        0.0f at 1200 with LinearOutSlowInEasing
//                    },
//                    repeatMode = RepeatMode.Restart
//                )
//            )
//        }
//    }
//
//    val circleValues = circles.map { it.value }
//    val distance = with(LocalDensity.current) { travelDistance.toPx() }
//
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
//    ) {
//        circleValues.forEach { value ->
//            Box(
//                modifier = Modifier
//                    .size(circleSize)
//                    .graphicsLayer {
//                        translationY = -value * distance
//                    }
//                    .background(
//                        color = circleColor,
//                        shape = CircleShape
//                    )
//            )
//        }
//    }
}

//@Composable
//fun Loader() {
//    val composition by rememberLottieComposition(LottieCompositionSpec)
//    val progress by animateLottieCompositionAsState(composition)
//    LottieAnimation(
//        composition = composition,
//        progress = { progress },
//    )
//}