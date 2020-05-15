package com.balash.currencytestapp.data

import io.reactivex.rxjava3.core.Scheduler


/**
 * Interface for providing different types of [Scheduler]s.
 */
interface IRxSchedulers {

    /**
     * Return [Scheduler] for computational work
     *
     *  @return [Scheduler]
     */
    fun computation(): Scheduler

    /**
     * Return [Scheduler] for working with network
     *
     *  @return [Scheduler]
     */
    fun io(): Scheduler

    /**
     * Return [Scheduler] for executing actions on the Android main thread
     *
     * @return [Scheduler]
     */
    fun ui(): Scheduler
}