package com.example.medoblock.features.shared.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val ScreenHorizontalPadding: Dp = 20.dp,
    val ScreenTopPaddingLg: Dp = 14.dp,
    val ScreenTopPaddingMd: Dp = 8.dp,
    val ScreenTopPaddingSm: Dp = 4.dp,
    val PaddingSm: Dp = 8.dp,
    val PaddingMd: Dp = 12.dp,
    val PaddingLg: Dp = 16.dp,
    val PaddingXlg: Dp = 20.dp,
    val CornerRadiusSm: Dp = 8.dp,
    val CornerRadiusMd: Dp = 12.dp,
    val CornerRadiusLg: Dp = 16.dp,
    val CornerRadiusXlg: Dp = 20.dp,
    val bottomButtonContainerHeight: Dp = 72.dp,
    val DividerLineStartPaddingLg: Dp = 64.dp,
    val DividerLineStartPaddingMd: Dp = 56.dp,
    val DividerLineStartPaddingSm: Dp = 52.dp
)

val LocalDimension = compositionLocalOf { Dimensions() }