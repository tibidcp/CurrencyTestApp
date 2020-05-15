package com.balash.currencytestapp.data.converter

import com.balash.currencytestapp.BaseParserTest
import com.balash.currencytestapp.R
import com.balash.currencytestapp.models.data.CurrencyItem
import com.balash.currencytestapp.models.domain.Currency
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Test on [CurrencyConverter]
 */
class CurrencyConverterTest : BaseParserTest() {

    private val filename = "example.json"
    private val euro = "EUR"

    private lateinit var converter: CurrencyConverter

    @Before
    fun setUp() {
        converter = CurrencyConverter(getResponse()!!)
    }

    @Test
    fun convert() {
        assertEquals(getExpected(), converter.convert())
    }

    private fun getExpected(): List<Currency> {
        val currency = ArrayList<Currency>()
        currency.add(Currency(euro, R.string.euro, 1.0, R.drawable.ic_eur))
        currency.add(Currency("AUD", R.string.aud, 1.6, R.drawable.ic_aud))
        currency.add(Currency("BGN", R.string.bgn, 1.963, R.drawable.ic_bgn))
        currency.add(Currency("BRL", R.string.brl, 4.215, R.drawable.ic_brl))
        return currency
    }

    private fun getResponse(): CurrencyItem? {
        return parse(filename, CurrencyItem::class.java)
    }
}