package com.balash.currencytestapp.data.repositories

import com.balash.currencytestapp.data.converter.CurrencyConverter
import com.balash.currencytestapp.data.mapper.CurrencyApiMapper
import com.balash.currencytestapp.domain.repositories.CurrencyRepository
import com.balash.currencytestapp.models.domain.Currency
import io.reactivex.rxjava3.core.Single

/**
 * Realization of [CurrencyRepository]
 */
class CurrencyRepositoryImpl(private val apiMapper: CurrencyApiMapper) : CurrencyRepository {

    override fun loadCurrency(base: String): Single<List<Currency>> {
        return apiMapper.loadCurrency(base).map { response ->
            CurrencyConverter(response).convert()
        }
    }
}