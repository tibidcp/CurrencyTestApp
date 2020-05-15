package com.balash.currencytestapp.models.domain

/**
 * Model for UI
 *
 * @property name        name of currency
 * @property description description of currency
 * @property value       value current value
 * @property image       url for image
 */
data class Currency(
    val name: String,
    val description: Int,
    val value: Double,
    val image: Int
)