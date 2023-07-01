package com.example.medoblock.core.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

object Extensions {

    fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }

    /* AZ. Not sure which method is better
    fun Context.findActivity(): Activity {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) return context
            context = context.baseContext
        }
        throw IllegalStateException("no activity")
    }
     */

    val <T> List<T>.getSafeFirst
        get() = if(this.isNotEmpty()) this[0] else null

    val Window.getSoftInputMode
        get() = attributes.softInputMode

    val String.color
        get() = Color(android.graphics.Color.parseColor(this))

    val Int.toDoubleDigitStr
        get() = if(this < 10) "0$this" else this.toString()

    val LazyListState.isScrolledToTheEnd
        get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    val String.countAfterLastDecimal: Int
        get(){
            val regEx = Regex("\\.")
            val haveDecimal = this.contains(regEx)
            if(!haveDecimal) return  0
            val splited = this.split(".")
            val lastSplit = if(splited.isNotEmpty()) splited[splited.size - 1] else ""
            return lastSplit.count()
        }

    val String.toSafeFloat: Float
        get() = this.toFloat()

    fun String.containsFromFirst(value: String): Boolean{
        val isContains = this.contains(value)
        val index = if(isContains) this.indexOf(value) else null
        return index == 0
    }

    val String.removeAllSpace: String
        get() = this.split(" ").joinToString("")

    val String.toSafeLong: Long?
        get() = try {
            val strValue: String = this
            val isNegative = strValue.contains("-")
            val result = if(isNegative){
                val negativeRemoved = strValue.substring(strValue.indexOf("-")+1,strValue.length)
                -negativeRemoved.toLong()
            }else{
                strValue.toLong()
            }
            result
        }catch (e: Exception){
            e.printStackTrace()
            null
        }

    fun String.onlyLetterText() = run {
        val pattern = Regex("[A-Za-z\\s]")
        pattern.containsMatchIn(this)
    }


    fun Modifier.conditional(
        condition: Boolean,
        ifTrue: (Modifier.() -> Modifier) = {this},
        ifFalse: (Modifier.() -> Modifier) = {this},
    ): Modifier {
        return if(condition) then(ifTrue(Modifier)) else then(ifFalse(Modifier))
    }

    fun Modifier.advancedShadow(
        color: Color = Color.Black,
        alpha: Float = 0f,
        cornersRadius: Dp = 0.dp,
        shadowBlurRadius: Dp = 0.dp,
        offsetY: Dp = 0.dp,
        offsetX: Dp = 0.dp
    ) = drawBehind {
        val shadowColor = color.copy(alpha = alpha).toArgb()
        val transparentColor = color.copy(alpha = 0f).toArgb()
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.color = transparentColor
            frameworkPaint.setShadowLayer(
                shadowBlurRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
            it.drawRoundRect(
                0f,
                0f,
                this.size.width,
                this.size.height,
                cornersRadius.toPx(),
                cornersRadius.toPx(),
                paint
            )
        }
    }

    fun View.isKeyboardOpen(): Boolean {
        val rect = Rect()
        getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.height
        val keypadHeight = screenHeight - rect.bottom;
        return keypadHeight > screenHeight * 0.15
    }

    @Composable
    fun rememberIsKeyboardOpen(view: View): State<Boolean> {
        return produceState(initialValue = view.isKeyboardOpen()) {
            val viewTreeObserver = view.viewTreeObserver
            val listener = ViewTreeObserver.OnGlobalLayoutListener { value = view.isKeyboardOpen() }
            viewTreeObserver.addOnGlobalLayoutListener(listener)
            awaitDispose { viewTreeObserver.removeOnGlobalLayoutListener(listener) }
        }
    }

    fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {
        var isFocused by remember { mutableStateOf(false) }
        var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }

        if (isFocused) {
            val isKeyboardOpen by rememberIsKeyboardOpen(LocalView.current)
            val focusManager = LocalFocusManager.current
            LaunchedEffect(isKeyboardOpen) {
                if (isKeyboardOpen) {
                    keyboardAppearedSinceLastFocused = true
                } else if (keyboardAppearedSinceLastFocused) {
                    focusManager.clearFocus()
                }
            }
        }
        onFocusEvent {
            if (isFocused != it.isFocused) {
                isFocused = it.isFocused
                if (isFocused) {
                    keyboardAppearedSinceLastFocused = false
                }
            }
        }
    }

    val LazyListState.isLastItemVisible: Boolean
        get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

    val LazyListState.isFirstItemVisible: Boolean
        get() = firstVisibleItemIndex == 0

    @Composable
    fun Modifier.verticalScrollbar(
        state: LazyListState,
        width: Dp = 8.dp,
        barColor: Color,
        visibleItemCount: Int? = null,
        hideShowOnScroll: Boolean = false
    ): Modifier {
        val targetAlpha = if (state.isScrollInProgress || !hideShowOnScroll) 1f else 0f
        val duration = if (state.isScrollInProgress) 150 else 500

        val alpha by animateFloatAsState(
            targetValue = targetAlpha,
            animationSpec = tween(durationMillis = duration)
        )

        return drawWithContent {
            drawContent()

            val firstVisibleElementIndex = state.layoutInfo.visibleItemsInfo.firstOrNull()?.index
            val needDrawScrollbar = state.isScrollInProgress || alpha > 0.0f

            if (needDrawScrollbar && firstVisibleElementIndex != null) {
                val elementHeight = (this.size.height / state.layoutInfo.totalItemsCount)
                val newVisibleItemCount = visibleItemCount ?: state.layoutInfo.visibleItemsInfo.size
                val scrollbarHeight = (newVisibleItemCount * (elementHeight))
                val scrollbarOffsetY = firstVisibleElementIndex * elementHeight + state.firstVisibleItemScrollOffset / 4

                drawRoundRect(
                    color = barColor,
                    topLeft = Offset(this.size.width - width.toPx(), scrollbarOffsetY),
                    size = Size(width.toPx(), scrollbarHeight),
                    alpha = alpha,
                    cornerRadius = CornerRadius(100.dp.toPx(), 100.dp.toPx()) //full rounded
                )
            }
        }
    }

    fun Modifier.gesturesDisabled(disabled: Boolean = true) =
        if (disabled) {
            pointerInput(Unit) {
                awaitPointerEventScope {
                    // we should wait for all new pointer events
                    while (true) {
                        awaitPointerEvent(pass = PointerEventPass.Initial)
                            .changes
                            .forEach(PointerInputChange::consume)
                    }
                }
            }
        } else this

    fun NavController.navigate(
        route: String,
        args: Bundle,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null,
        //builder: NavOptionsBuilder.() -> Unit
    ) {
        val routeLink = NavDeepLinkRequest
            .Builder
            .fromUri(NavDestination.createRoute(route).toUri())
            .build()

        val deepLinkMatch = graph.matchDeepLink(routeLink)
        if (deepLinkMatch != null) {
            val destination = deepLinkMatch.destination
            val id = destination.id
            navigate(id, args, navOptions, navigatorExtras)
        } else {
            navigate(route, navOptions, navigatorExtras)
        }
    }

    fun Modifier.parentPaddingOverflow(width: Dp = 0.dp): Modifier {
        return this.layout { measurable, constraints ->
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = constraints.maxWidth + width.roundToPx() + 16.dp.roundToPx()
                )
            )
            layout(placeable.width, placeable.height){
                placeable.place(0,0)
            }
        }
    }
}