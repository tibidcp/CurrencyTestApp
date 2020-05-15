package com.balash.currencytestapp.data

import com.balash.currencytestapp.models.data.CurrencyItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for creating Retrofit service
 */
interface CurrencyService {

    /**
     * Returns the latest rates for base currency
     *
     * @param base the base currency
     * @return result of request
     */
    @GET("latest")
    fun getCurrencyRates(@Query("base") base: String): Single<CurrencyItem>
}