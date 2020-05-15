package com.balash.currencytestapp.domain.repositories

import com.balash.currencytestapp.models.domain.Currency
import io.reactivex.rxjava3.core.Single

/**
 * Interface of repository for loading currency
 */
interface CurrencyRepository {

    /**
     * Load currency rate
     *
     * @param base the base element
     */
    fun loadCurrency(base: String): Single<List<Currency>>
}