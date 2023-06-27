package com.example.medoblock.features.shared.components

import com.example.medoblock.R
import com.example.medoblock.features.shared.utils.LocalDimension
import com.example.medoblock.features.ui.theme.MedoBlockTheme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    showDivider: Boolean = false,
    showNavIcon: Boolean = true,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    navIconColor: Color = MaterialTheme.colorScheme.onBackground,
    actionIconColor: Color = MaterialTheme.colorScheme.primary,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    prefix: @Composable (RowScope.() -> Unit) = {},
    actions: @Composable (RowScope.() -> Unit) = {},
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.background(backgroundColor)){
            TopAppBar(
                modifier = Modifier,
                scrollBehavior = scrollBehavior,
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    Row( verticalAlignment = Alignment.CenterVertically ) {
                        if(showNavIcon){
                            IconButton(onClick = { onBack() }) {
                                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                            }
                        }
                        if(!showNavIcon) leftPadding()
                        prefix()
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = titleColor,
                    navigationIconContentColor = navIconColor,
                    actionIconContentColor = actionIconColor,
                    scrolledContainerColor = Color.Transparent
                ),
                actions = {
                    actions()
                    rightPadding()
                }
            )
        }

        if(showDivider){
            val isVisible = scrollBehavior == null ||
                    (scrollBehavior.state.collapsedFraction == -0f
                            && scrollBehavior.state.overlappedFraction ==0f)

            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(tween(600)),
                exit = fadeOut(tween(600))
            ) {
                MDivider.TitleBarDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MTopAppBarCenterAligned(
    title: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    showDivider: Boolean = false,
    showNavIcon: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    navIconColor: Color = MaterialTheme.colorScheme.onBackground,
    actionIconColor: Color = MaterialTheme.colorScheme.primary,
    prefix: @Composable (RowScope.() -> Unit) = {},
    actions: @Composable (RowScope.() -> Unit) = {},
) {
    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                Row( verticalAlignment = Alignment.CenterVertically ) {
                    if(showNavIcon){
                        IconButton(onClick = { onBack() }) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                        }
                    }
                    if(!showNavIcon) leftPadding()
                    prefix()
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = backgroundColor,
                titleContentColor = titleColor,
                navigationIconContentColor = navIconColor,
                actionIconContentColor = actionIconColor
            ),
            actions = {
                actions()
                rightPadding()
            }
        )
        if(showDivider){
            val isVisible = scrollBehavior == null ||
                    (scrollBehavior.state.collapsedFraction == -0f
                            && scrollBehavior.state.overlappedFraction ==0f)

            AnimatedVisibility(visible = isVisible) {
                MDivider.TitleBarDivider()
            }
        }
    }
}

private val leftPadding = @Composable{
    val dimension = LocalDimension.current
    val containerXPadding = dimension.ScreenHorizontalPadding
    Spacer(modifier = Modifier.width(containerXPadding - 8.dp))
}

private val rightPadding = @Composable{
    val dimension = LocalDimension.current
    val containerXPadding = dimension.ScreenHorizontalPadding
    Spacer(modifier = Modifier.width(containerXPadding - 12.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewAppTopBar1() {
    MedoBlockTheme() {
        MTopAppBar(
            title = "Home",
            showDivider = true,
            actions = {
                TopAppBarIcon(iconId = R.drawable.chatbot, iconColor = Color.Red) {}
                TopAppBarIcon(iconId = R.drawable.chatbot) {}
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewAppTopBar2() {
    MTopAppBar(title = "Header")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewAppTopBar3() {
    MTopAppBar(
        title = "Header",
        actions = {
            TopAppBarTextButton(
                buttonText = "Button",
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewAppTopBar4() {
    MTopAppBar(
        title = "Header",
        showNavIcon = false,
        actions = {
            TopAppBarTextButton(
                buttonText = "Button",
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewCenterAppTopBar1() {
    MedoBlockTheme {
        MTopAppBarCenterAligned(
            title = "Home",
            showDivider = true,
            actions = {
                TopAppBarIcon(iconId = R.drawable.chatbot, iconColor = Color.Red) {}
                TopAppBarIcon(iconId = R.drawable.chatbot) {}
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewCenterAppTopBar2() {
    MTopAppBarCenterAligned(title = "Header")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewCenterAppTopBar3() {
    MTopAppBarCenterAligned(
        title = "Header",
        actions = {
            TopAppBarTextButton(
                buttonText = "Button",
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun PreviewCenterAppTopBar4() {
    MTopAppBarCenterAligned(
        title = "Header",
        showNavIcon = false,
        actions = {
            TopAppBarTextButton(
                buttonText = "Button",
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    )
}