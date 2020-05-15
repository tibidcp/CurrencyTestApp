package com.balash.currencytestapp.presentation.adapter

/**
 * Interface for item click listener
 */
interface BaseItemListener {

    /**
     * @param newBase the new base item
     */
    fun onBaseChanged(newBase: String)

}