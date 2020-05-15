package com.balash.currencytestapp.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.balash.currencytestapp.models.domain.Currency

/**
 * DiffUtilCallback for items in list
 */
class CurrencyDiffUtilCallback : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.value == newItem.value
    }

    override fun getChangePayload(oldItem: Currency, newItem: Currency): Pair<Currency, Currency> {
        return Pair(oldItem, newItem)
    }
}