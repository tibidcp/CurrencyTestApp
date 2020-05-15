package com.balash.currencytestapp

import android.app.Application
import com.balash.currencytestapp.di.components.CurrencyAppComponent
import com.balash.currencytestapp.di.components.DaggerCurrencyAppComponent
import com.balash.currencytestapp.di.modules.ApplicationModule

/**
 * Realization of application for current app
 */
class CurrencyApplication : Application() {
    private lateinit var currencyAppComponent: CurrencyAppComponent

    override fun onCreate() {
        super.onCreate()
        currencyAppComponent = initDagger(this)
    }

    private fun initDagger(app: CurrencyApplication): CurrencyAppComponent =
        DaggerCurrencyAppComponent.builder()
            .applicationModule(ApplicationModule(app))
            .build()

    fun getComponent(): CurrencyAppComponent {
        return currencyAppComponent
    }
}