package com.example.detection.utils

fun String.encodeEmail(): String = replace('.', ',')
fun String.decodeEmail(): String =replace(',', '.')