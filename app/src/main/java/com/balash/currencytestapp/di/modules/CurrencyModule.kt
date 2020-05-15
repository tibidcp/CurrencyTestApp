package com.balash.currencytestapp.di.modules

import com.balash.currencytestapp.data.IRxSchedulers
import com.balash.currencytestapp.data.IRxSchedulersImpl
import com.balash.currencytestapp.data.mapper.CurrencyApiMapper
import com.balash.currencytestapp.data.mapper.CurrencyApiMapperImpl
import com.balash.currencytestapp.data.repositories.CurrencyRepositoryImpl
import com.balash.currencytestapp.domain.interactors.CurrencyInteractor
import com.balash.currencytestapp.domain.interactors.CurrencyInteractorImpl
import com.balash.currencytestapp.domain.repositories.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module for providing dependencies for current app
 */
@Module
class CurrencyModule {

    @Singleton
    @Provides
    fun provideRxScheduler(): IRxSchedulers {
        return IRxSchedulersImpl();
    }

    @Provides
    fun provideCurrencyInteractor(
        currencyRepository: CurrencyRepository
    ): CurrencyInteractor {
        return CurrencyInteractorImpl(currencyRepository)
    }

    @Provides
    fun provideCurrencyRepository(
        currencyApiMapper: CurrencyApiMapper
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyApiMapper)
    }

    @Singleton
    @Provides
    fun provideCurrencyApiMapper(): CurrencyApiMapper {
        return CurrencyApiMapperImpl()
    }
}