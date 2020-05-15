package com.balash.currencytestapp.presentation.view

import androidx.annotation.StringRes
import com.balash.currencytestapp.presentation.presenter.BasePresenter

/**
 * Interface with general methods for view
 */
interface BaseView {
    /**
     * Set presenter for view
     *
     * @param presenter any presenter
     */
    fun setPresenter(presenter: BasePresenter)

    /**
     * Show loading indicator
     */
    fun showLoading()

    /**
     * Hide loading indicator
     */
    fun hideLoading()


    /**
     * Show error
     *
     * @param errorStringId string id from resource
     */
    fun showError(@StringRes errorStringId: Int)
}