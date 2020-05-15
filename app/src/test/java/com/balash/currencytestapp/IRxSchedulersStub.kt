package com.balash.currencytestapp

import com.balash.currencytestapp.data.IRxSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Stub realization for [IRxSchedulers]
 */
class IRxSchedulersStub : IRxSchedulers {
    override fun computation(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }
}