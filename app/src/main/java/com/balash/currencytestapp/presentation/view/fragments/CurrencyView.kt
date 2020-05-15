package com.balash.currencytestapp.presentation.view.fragments

import com.balash.currencytestapp.models.domain.Currency
import com.balash.currencytestapp.presentation.view.BaseView

/**
 * Interface for CurrencyFragment
 */
interface CurrencyView : BaseView {

    /**
     * Update information about currency
     *
     * @param currency the latest information
     */
    fun showCurrency(currency: List<Currency>)

    /**
     * Change UI if error
     */
    fun onError()
}