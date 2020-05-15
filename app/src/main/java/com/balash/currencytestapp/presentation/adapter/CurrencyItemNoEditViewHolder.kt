package com.balash.currencytestapp.presentation.adapter

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.balash.currencytestapp.R
import com.balash.currencytestapp.models.domain.Currency
import com.balash.currencytestapp.utils.formatDouble
import kotlinx.android.synthetic.main.currency_item.view.ivIcon
import kotlinx.android.synthetic.main.currency_item.view.tvDescriptionOfCurrency
import kotlinx.android.synthetic.main.currency_item.view.tvNameOfCurrency
import kotlinx.android.synthetic.main.currency_item_no_edit.view.*

/**
 * ViewHolder for no editable item
 */
class CurrencyItemNoEditViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    /**
     * Bind data to view
     *
     * @param currency    currency item
     * @param coefficient coefficient
     */
    fun bind(currency: Currency, coefficient: Double) {
        val drawable = AppCompatResources.getDrawable(itemView.context, currency.image)
        itemView.ivIcon.setImageDrawable(drawable)
        itemView.tvNameOfCurrency.text = currency.name
        itemView.tvDescriptionOfCurrency.setText(currency.description)
        itemView.tvValue.text = formatDouble(currency.value.times(coefficient))
        itemView.underText.setBackgroundColor(
            ContextCompat.getColor(
                itemView.context,
                R.color.light_grey
            )
        )
    }
}