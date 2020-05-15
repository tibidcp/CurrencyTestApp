package com.balash.currencytestapp.data.mapper

import com.balash.currencytestapp.BuildConfig
import com.balash.currencytestapp.data.CurrencyService
import com.balash.currencytestapp.models.data.CurrencyItem
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://hiring.revolut.codes/api/android/"

/**
 * Realization of [CurrencyApiMapper]
 */
class CurrencyApiMapperImpl : CurrencyApiMapper {

    private var currencyService: CurrencyService

    init {
        currencyService = buildRetrofit().create(CurrencyService::class.java)
    }

    override fun loadCurrency(base: String): Single<CurrencyItem> {
        return currencyService.getCurrencyRates(base)
    }

    private val interceptor: HttpLoggingInterceptor
        get() = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level =
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
            }
        }

    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }


    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}