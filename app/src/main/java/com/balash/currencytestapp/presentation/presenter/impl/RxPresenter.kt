package com.balash.currencytestapp.presentation.presenter.impl

import com.balash.currencytestapp.R
import com.balash.currencytestapp.data.IRxSchedulers
import com.balash.currencytestapp.presentation.presenter.BasePresenter
import com.balash.currencytestapp.presentation.view.BaseView
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.InetAddress
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


abstract class RxPresenter<V : BaseView>(
    val view: V,
    val rxSchedulers: IRxSchedulers
) : BasePresenter {

    private val mCompositeDisposable = CompositeDisposable()

    init {
        view.setPresenter(this)
    }

    override fun unsubscribe() {
        mCompositeDisposable.clear()
    }

    /**
     * Add disposable
     *
     * @param d disposable
     */
    protected fun addDisposable(d: Disposable) {
        mCompositeDisposable.add(d)
    }

    /**
     * Add single disposable
     *
     * @param d disposable
     */
    protected fun addSingleDisposable(d: Disposable) {
        mCompositeDisposable.clear()
        mCompositeDisposable.add(d)
    }

    /**
     * Handle error
     *
     * @param t error
     */
    open fun handleApiError(t: Throwable) {
        if (t.javaClass == ConnectException::class.java
            || t.javaClass == SocketTimeoutException::class.java
            || t.javaClass == UnknownHostException::class.java
            || t.javaClass == HttpException::class.java
            || t.javaClass == SSLHandshakeException::class.java
        ) {
            checkInternetAvailability()
            return
        } else {
            t.printStackTrace()
            view.showError(R.string.error_unknown)
        }
        view.hideLoading()
    }

    private fun checkInternetAvailability() {
        mCompositeDisposable.clear()
        mCompositeDisposable.add(
            Single.fromCallable { isInternetAvailable() }
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.ui())
                .subscribe(
                    { isInternetAvailable ->
                        if (isInternetAvailable) {
                            view.showError(R.string.error_server_error)
                        } else {
                            view.showError(R.string.error_no_connection)
                        }
                        view.hideLoading()
                    }, { t: Throwable -> handleApiError(t) })
        )
    }

    private fun isInternetAvailable(): Boolean {
        try {
            return InetAddress.getByName("google.com").hostAddress != ""
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return false
    }
}