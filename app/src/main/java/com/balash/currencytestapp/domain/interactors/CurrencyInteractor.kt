package com.balash.currencytestapp.domain.interactors

import com.balash.currencytestapp.models.domain.Currency
import io.reactivex.rxjava3.core.Single

/**
 * Interactor for loading currency rate
 */
interface CurrencyInteractor {

    /**
     * Load currency rate
     *
     * @param base the base element
     */
    fun loadCurrency(base: String) : Single<List<Currency>>
}