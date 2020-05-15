package com.balash.currencytestapp.utils

import java.math.RoundingMode
import java.text.DecimalFormat

fun formatDouble(value: Double): String {
    val decimalFormat = DecimalFormat("0.00")
    decimalFormat.roundingMode = RoundingMode.HALF_UP
    decimalFormat.isDecimalSeparatorAlwaysShown = false
    return decimalFormat.format(value)
}