package com.balash.currencytestapp.domain.interactors

import com.balash.currencytestapp.domain.repositories.CurrencyRepository
import com.balash.currencytestapp.models.domain.Currency
import io.reactivex.rxjava3.core.Single

/**
 * Realization off [CurrencyInteractor]
 */
class CurrencyInteractorImpl(private val currencyRepository: CurrencyRepository) :
    CurrencyInteractor {

    override fun loadCurrency(base: String): Single<List<Currency>> {
        return currencyRepository.loadCurrency(base)
    }
}