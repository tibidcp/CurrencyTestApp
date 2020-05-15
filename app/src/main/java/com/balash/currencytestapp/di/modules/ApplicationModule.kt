package com.balash.currencytestapp.di.modules

import android.app.Application
import android.content.Context
import com.balash.currencytestapp.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Dagger module for providing [ApplicationContext]
 */
@Module
class ApplicationModule(private val application: Application) {

    @ApplicationContext
    @Provides
    internal fun provideContext(): Context {
        return application
    }
}