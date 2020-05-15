package com.balash.currencytestapp.presentation.presenter

/**
 * Presenter for CurrencyView
 */
interface CurrencyPresenter : BasePresenter {

    /**
     * Load cats
     *
     * @param showLoading  need show progress
     */
    fun loadCurrency(showLoading: Boolean)

    /**
     * Change base currency
     *
     * @param newBase the new base
     */
    fun onItemClick(newBase: String)
}