package com.balash.currencytestapp.data.converter

import com.balash.currencytestapp.R
import com.balash.currencytestapp.models.data.CurrencyItem
import com.balash.currencytestapp.models.domain.Currency

/**
 * Converter from [CurrencyItem] to List[Currency]
 */
class CurrencyConverter(private val from: CurrencyItem) {

    fun convert(): List<Currency> {
        val to = ArrayList<Currency>()
        var helpItem = getResourcePair(from.baseCurrency)
        to.add(
            Currency(
                from.baseCurrency, helpItem.second, 1.0,
                helpItem.first
            )
        )
        from.rates.map {
            helpItem = getResourcePair(it.key)
            to.add(Currency(it.key, helpItem.second, it.value, helpItem.first))
        }
        return to
    }

    private fun getResourcePair(base: String): Pair<Int, Int> {
        when (base) {
            "EUR" -> return Pair(R.drawable.ic_eur, R.string.euro)
            "AUD" -> return Pair(R.drawable.ic_aud, R.string.aud)
            "BGN" -> return Pair(R.drawable.ic_bgn, R.string.bgn)
            "BRL" -> return Pair(R.drawable.ic_brl, R.string.brl)
            "CAD" -> return Pair(R.drawable.ic_cad, R.string.cad)
            "CHF" -> return Pair(R.drawable.ic_chf, R.string.chf)
            "CNY" -> return Pair(R.drawable.ic_cny, R.string.cny)
            "CZK" -> return Pair(R.drawable.ic_czk, R.string.czk)
            "DKK" -> return Pair(R.drawable.ic_dkk, R.string.dkk)
            "GBP" -> return Pair(R.drawable.ic_gbp, R.string.gbp)
            "HKD" -> return Pair(R.drawable.ic_hkd, R.string.hkd)
            "HRK" -> return Pair(R.drawable.ic_hrk, R.string.hrk)
            "HUF" -> return Pair(R.drawable.ic_huf, R.string.huf)
            "IDR" -> return Pair(R.drawable.ic_idr, R.string.idr)
            "ILS" -> return Pair(R.drawable.ic_ils, R.string.ils)
            "INR" -> return Pair(R.drawable.ic_inr, R.string.inr)
            "JPY" -> return Pair(R.drawable.ic_jpy, R.string.jpy)
            "KRW" -> return Pair(R.drawable.ic_krw, R.string.krw)
            "MXN" -> return Pair(R.drawable.ic_mxn, R.string.mxn)
            "MYR" -> return Pair(R.drawable.ic_myr, R.string.myr)
            "NOK" -> return Pair(R.drawable.ic_nok, R.string.nok)
            "NZD" -> return Pair(R.drawable.ic_nzd, R.string.nzd)
            "PHP" -> return Pair(R.drawable.ic_php, R.string.php)
            "PLN" -> return Pair(R.drawable.ic_pln, R.string.pln)
            "RON" -> return Pair(R.drawable.ic_ron, R.string.ron)
            "RUB" -> return Pair(R.drawable.ic_rub, R.string.rub)
            "SEK" -> return Pair(R.drawable.ic_sek, R.string.sek)
            "SGD" -> return Pair(R.drawable.ic_sgd, R.string.sgd)
            "THB" -> return Pair(R.drawable.ic_thb, R.string.thb)
            "USD" -> return Pair(R.drawable.ic_usd, R.string.usd)
            "ZAR" -> return Pair(R.drawable.ic_zar, R.string.zar)
            "ISK" -> return Pair(R.drawable.ic_isk, R.string.isk)
        }
        return Pair(R.drawable.ic_rub, R.string.rub)
    }
}