package com.balash.currencytestapp.presentation.presenter.impl

import com.balash.currencytestapp.data.IRxSchedulers
import com.balash.currencytestapp.domain.interactors.CurrencyInteractor
import com.balash.currencytestapp.presentation.presenter.CurrencyPresenter
import com.balash.currencytestapp.presentation.view.fragments.CurrencyView
import java.util.concurrent.TimeUnit

/**
 * Realization of [CurrencyPresenter]
 */
class CurrencyPresenterImpl(
    view: CurrencyView,
    rxScheduler: IRxSchedulers,
    private val currencyInteractor: CurrencyInteractor
) : RxPresenter<CurrencyView>(view, rxScheduler), CurrencyPresenter {

    private var base = "EUR"

    override fun subscribe() {
        loadCurrency(true)
    }

    override fun loadCurrency(showLoading: Boolean) {
        if (showLoading) {
            view.showLoading()
        }
        addSingleDisposable(
            currencyInteractor.loadCurrency(base)
                .repeatWhen { completed -> completed.delay(1, TimeUnit.SECONDS) }
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .subscribe(
                    { c ->
                        view.hideLoading()
                        view.showCurrency(c)
                    },
                    ::handleApiError,
                    view::hideLoading
                )
        )
    }

    override fun onItemClick(newBase: String) {
        base = newBase
        loadCurrency(false)
    }

    override fun handleApiError(t: Throwable) {
        super.handleApiError(t)
        view.onError()
    }
}