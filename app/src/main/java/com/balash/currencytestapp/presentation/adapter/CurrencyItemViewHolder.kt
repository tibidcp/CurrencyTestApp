package com.balash.currencytestapp.presentation.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.balash.currencytestapp.models.domain.Currency
import com.balash.currencytestapp.utils.formatDouble
import kotlinx.android.synthetic.main.currency_item.view.*

/**
 * ViewHolder for editable item
 */
class CurrencyItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind data to view
     *
     * @param currency  currency item
     * @param value     value
     */
    fun bind(currency: Currency, value: Double) {
        val drawable = AppCompatResources.getDrawable(itemView.context, currency.image)
        itemView.ivIcon.setImageDrawable(drawable)
        itemView.tvNameOfCurrency.text = currency.name
        itemView.tvDescriptionOfCurrency.setText(currency.description)
        itemView.etValue.setText(formatDouble(value))
        itemView.etValue.setSelection(itemView.etValue.editableText.length)
    }
}