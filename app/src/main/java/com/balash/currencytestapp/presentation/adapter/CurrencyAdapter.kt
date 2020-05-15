package com.balash.currencytestapp.presentation.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.balash.currencytestapp.R
import com.balash.currencytestapp.models.domain.Currency
import com.balash.currencytestapp.utils.formatDouble
import kotlinx.android.synthetic.main.currency_item.view.*

/**
 * Adapter for showing items in list
 */
class CurrencyAdapter(
    private val context: Context?,
    private val listener: BaseItemListener
) : ListAdapter<Currency, RecyclerView.ViewHolder>(CurrencyDiffUtilCallback()) {

    private var coefficient: Double = 1.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        if (viewType == 0) {
            return CurrencyItemViewHolder(
                layoutInflater.inflate(
                    R.layout.currency_item,
                    parent,
                    false
                )
            )
        } else {
            return CurrencyItemNoEditViewHolder(
                layoutInflater.inflate(
                    R.layout.currency_item_no_edit,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 0 else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            bindEditItem(holder, position)
        } else {
            bindNoEditItem(holder, position)
        }
    }

    private fun bindEditItem(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CurrencyItemViewHolder
        holder.bind(getItem(position), coefficient)
        holder.itemView.etValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editable: Editable?) {
                coefficient = editable.toString().toDoubleOrNull() ?: 0.0
            }
        })
    }

    private fun bindNoEditItem(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CurrencyItemNoEditViewHolder
        holder.bind(getItem(position), coefficient)
        holder.itemView.setOnClickListener {
            listener.onBaseChanged(getItem(position).name)
            notifyItemMoved(position, 0)
        }
    }
}