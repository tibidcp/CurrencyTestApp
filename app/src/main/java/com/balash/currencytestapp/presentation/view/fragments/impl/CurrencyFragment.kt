package com.balash.currencytestapp.presentation.view.fragments.impl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.balash.currencytestapp.R
import com.balash.currencytestapp.models.domain.Currency
import com.balash.currencytestapp.presentation.adapter.BaseItemListener
import com.balash.currencytestapp.presentation.adapter.CurrencyAdapter
import com.balash.currencytestapp.presentation.presenter.CurrencyPresenter
import com.balash.currencytestapp.presentation.view.fragments.CurrencyView
import com.balash.currencytestapp.utils.ConnectionLiveData
import kotlinx.android.synthetic.main.fragment_currency.*

/**
 * Fragment for [CurrencyView]
 */
class CurrencyFragment : RxView<CurrencyPresenter>(), CurrencyView, BaseItemListener {

    private lateinit var adapter: CurrencyAdapter
    private lateinit var connectionLiveData: ConnectionLiveData

    companion object {
        fun newInstance(): CurrencyFragment {
            return CurrencyFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionLiveData = ConnectionLiveData(requireContext())
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        getPresenter().subscribe()
    }

    override fun onPause() {
        super.onPause()
        getPresenter().unsubscribe()
    }

    override fun showCurrency(currency: List<Currency>) {
        if (connectionLiveData.hasObservers()) {
            connectionLiveData.removeObservers(this)
        }
        rvCurrency.visibility = View.VISIBLE
        tvWrong.visibility = View.GONE
        adapter.submitList(currency)
    }

    override fun onBaseChanged(newBase: String) {
        getPresenter().onItemClick(newBase)
    }

    override fun onError() {
        rvCurrency.visibility = View.GONE
        tvWrong.visibility = View.VISIBLE
        connectionLiveData.observe(this, Observer {
            if (it) getPresenter().loadCurrency(false)
        })
    }

    private fun initRecyclerView() {
        rvCurrency.layoutManager = LinearLayoutManager(context)
        rvCurrency.setHasFixedSize(true)
        adapter = CurrencyAdapter(context, this)
        rvCurrency.adapter = adapter
    }
}