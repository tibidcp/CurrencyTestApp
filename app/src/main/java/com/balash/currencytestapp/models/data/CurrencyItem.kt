package com.balash.currencytestapp.models.data

import com.google.gson.annotations.SerializedName

/**
 * @property baseCurrency   base elements
 * @property rates          current rates
 */
data class CurrencyItem(
    @SerializedName("baseCurrency")
    val baseCurrency: String,
    @SerializedName("rates")
    val rates: Map<String, Double>
)