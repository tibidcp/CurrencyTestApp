package com.balash.currencytestapp.data.mapper

import com.balash.currencytestapp.models.data.CurrencyItem
import io.reactivex.rxjava3.core.Single

/**
 * Interface for Api mapper
 */
interface CurrencyApiMapper {

    /**
     * Load data for base currency
     *
     * @return [CurrencyItem]
     */
    fun loadCurrency(base: String): Single<CurrencyItem>
}