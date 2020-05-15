package com.balash.currencytestapp.presentation.presenter.impl

import com.balash.currencytestapp.IRxSchedulersStub
import com.balash.currencytestapp.domain.interactors.CurrencyInteractor
import com.balash.currencytestapp.models.domain.Currency
import com.balash.currencytestapp.presentation.presenter.CurrencyPresenter
import com.balash.currencytestapp.presentation.view.fragments.CurrencyView
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Test

import org.junit.Before

/**
 * Test on [CurrencyPresenterImpl]
 */
class CurrencyPresenterImplTest {

    private val euro = "EUR"
    private val view: CurrencyView = spyk()
    private val interactor: CurrencyInteractor = mockk()

    private lateinit var presenter: CurrencyPresenter

    @Before
    fun setup() {
        presenter = CurrencyPresenterImpl(view, IRxSchedulersStub(), interactor)
    }

    @Test
    fun subscribe() {
        val items = getItems()
        every { interactor.loadCurrency(euro) } returns Single.just(items)
        presenter.subscribe()
        verify { interactor.loadCurrency(euro) }
        verify { view.hideLoading() }
        verify { view.showCurrency(items) }
    }

    @Test
    fun loadCurrency() {
        val items = getItems()
        every { interactor.loadCurrency(euro) } returns Single.just(items)
        presenter.loadCurrency(true)
        verify { view.showLoading() }
        verify { interactor.loadCurrency(euro) }
        verify { view.hideLoading() }
        verify { view.showCurrency(items) }
    }

    @Test
    fun loadCurrency_error() {
        every { interactor.loadCurrency(euro) } returns Single.error(Throwable("error"))
        presenter.loadCurrency(false)
        verify { interactor.loadCurrency(euro) }
        verify { view.hideLoading() }
        verify { view.onError() }
    }

    @Test
    fun onItemClick() {
        val items = getItems()
        every { interactor.loadCurrency(euro) } returns Single.just(items)
        presenter.onItemClick(euro)
        verify { interactor.loadCurrency(euro) }
        verify { view.hideLoading() }
        verify { view.showCurrency(items) }
    }

    private fun getItems(): List<Currency> {
        val list = ArrayList<Currency>()
        list.add(Currency(euro, 1, 1.0, 1))
        list.add(Currency("usd", 2, 1.5, 2))
        return list
    }
}