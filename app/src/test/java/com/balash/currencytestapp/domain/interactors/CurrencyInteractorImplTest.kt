package com.balash.currencytestapp.domain.interactors

import com.balash.currencytestapp.domain.repositories.CurrencyRepository
import com.balash.currencytestapp.models.domain.Currency
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

/**
 * Test on [CurrencyInteractorImpl]
 */
class CurrencyInteractorImplTest {

    private val repository: CurrencyRepository = mockk()
    private val usd = "usd"

    private lateinit var interactor: CurrencyInteractor

    @Before
    fun setup() {
        interactor = CurrencyInteractorImpl(repository)
    }

    @Test
    fun loadCurrency() {
        val items = getItems()
        every { repository.loadCurrency(usd) } returns Single.just(items)
        interactor.loadCurrency(usd).test().assertResult(items)
        verify { repository.loadCurrency(usd) }
    }

    private fun getItems(): List<Currency> {
        val list = ArrayList<Currency>()
        list.add(Currency(usd, 1, 1.0, 1))
        list.add(Currency("euro", 2, 1.5, 2))
        return list
    }
}