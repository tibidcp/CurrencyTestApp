package com.balash.currencytestapp.di.components

import com.balash.currencytestapp.data.IRxSchedulers
import com.balash.currencytestapp.di.modules.ApplicationModule
import com.balash.currencytestapp.di.modules.CurrencyModule
import com.balash.currencytestapp.domain.interactors.CurrencyInteractor
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        CurrencyModule::class]
)
interface CurrencyAppComponent {

    fun rxSchedulers(): IRxSchedulers

    fun currencyInteractor(): CurrencyInteractor
}