package com.kaizenplus.deloittecodechallenge

import androidx.compose.runtime.Composable

data class TabItem(
    val text: String,//Tab Title
    val screen: @Composable ()->Unit//Tab Screen(can also take params)
)
