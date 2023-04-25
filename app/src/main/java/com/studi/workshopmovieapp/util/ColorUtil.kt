package com.studi.workshopmovieapp.util

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat

private val colorList = listOf<Int>(
    android.R.color.holo_blue_light,
    android.R.color.holo_green_light,
    android.R.color.holo_orange_light,
    android.R.color.holo_purple,
    android.R.color.holo_red_light
)

fun Context.getRandomColor(): Int {
    return ContextCompat.getColor(this, colorList.random())
}

fun Context.getColorByOrder(position: Int): Int {
    return ContextCompat.getColor(this, colorList[position % colorList.size])
}