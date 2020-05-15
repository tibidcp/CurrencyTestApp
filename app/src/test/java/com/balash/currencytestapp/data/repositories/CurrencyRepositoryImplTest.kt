package com.balash.currencytestapp.data.repositories

import com.balash.currencytestapp.BaseParserTest
import com.balash.currencytestapp.R
import com.balash.currencytestapp.data.mapper.CurrencyApiMapper
import com.balash.currencytestapp.domain.repositories.CurrencyRepository
import com.balash.currencytestapp.models.data.CurrencyItem
import com.balash.currencytestapp.models.domain.Currency
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test

class CurrencyRepositoryImplTest : BaseParserTest() {

    private val filename = "example.json"
    private val apiMapper: CurrencyApiMapper = mockk()
    private val euro = "EUR"

    private lateinit var repository: CurrencyRepository

    @Before
    fun setUp() {
        repository = CurrencyRepositoryImpl(apiMapper)
    }

    @Test
    fun loadCurrency() {
        every { apiMapper.loadCurrency(euro) } returns Single.just(getResponse())
        repository.loadCurrency(euro).test().assertResult(getExpected())
        verify { apiMapper.loadCurrency(euro) }
    }

    private fun getResponse(): CurrencyItem? {
        return parse(filename, CurrencyItem::class.java)
    }

    private fun getExpected(): List<Currency> {
        val currency = ArrayList<Currency>()
        currency.add(Currency(euro, R.string.euro, 1.0, R.drawable.ic_eur))
        currency.add(Currency("AUD", R.string.aud, 1.6, R.drawable.ic_aud))
        currency.add(Currency("BGN", R.string.bgn, 1.963, R.drawable.ic_bgn))
        currency.add(Currency("BRL", R.string.brl, 4.215, R.drawable.ic_brl))
        return currency
    }
}