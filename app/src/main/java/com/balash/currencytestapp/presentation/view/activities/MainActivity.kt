package com.balash.currencytestapp.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.balash.currencytestapp.CurrencyApplication
import com.balash.currencytestapp.R
import com.balash.currencytestapp.presentation.presenter.impl.CurrencyPresenterImpl
import com.balash.currencytestapp.presentation.view.fragments.impl.CurrencyFragment
import com.balash.currencytestapp.utils.ConnectionLiveData

/**
 * Realization of Activity for current app
 */
class MainActivity : AppCompatActivity() {

    private val content = R.id.contentFrame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        var currencyFragment = supportFragmentManager.findFragmentById(content)
        if (currencyFragment == null) {
            currencyFragment = CurrencyFragment.newInstance()
            addFragmentToActivity(supportFragmentManager, currencyFragment)
        }
        val component = (application as CurrencyApplication).getComponent()
        val presenter = CurrencyPresenterImpl(
            currencyFragment as CurrencyFragment,
            component.rxSchedulers(),
            component.currencyInteractor()
        )
    }

    /**
     * The fragment is added to the container view with id frameId. The operation is
     * performed by the fragmentManager.
     *
     * @param fragmentManager fragment manager
     * @param fragment        fragment
     */
    private fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(content, fragment)
        transaction.commit()
    }

}
