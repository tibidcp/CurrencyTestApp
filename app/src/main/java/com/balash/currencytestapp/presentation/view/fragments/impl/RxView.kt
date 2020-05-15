package com.balash.currencytestapp.presentation.view.fragments.impl

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.balash.currencytestapp.R
import com.balash.currencytestapp.presentation.presenter.BasePresenter
import com.balash.currencytestapp.presentation.view.BaseView
import com.google.android.material.snackbar.Snackbar


abstract class RxView<T : BasePresenter> : Fragment(), BaseView {

    private lateinit var presenter: T
    private var coordinatorLayout: CoordinatorLayout? = null
    private var progressContainer: LinearLayout? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity: Activity? = activity
        if (activity != null) {
            coordinatorLayout = activity.findViewById(R.id.coordinatorLayout)
            progressContainer = activity.findViewById(R.id.progressContainer)
        }
    }


    override fun showError(@StringRes errorStringId: Int) {
        hideLoading()
        if (isAdded && coordinatorLayout != null) {
            val snackbar: Snackbar =
                Snackbar.make(coordinatorLayout!!, errorStringId, Snackbar.LENGTH_LONG)
            snackbar.duration = 5000
            snackbar.setAction(R.string.close_button) { snackbar.dismiss() }
            val textView: TextView =
                snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text)
            textView.maxLines = 5
            snackbar.show()
        }
    }

    final override fun setPresenter(presenter: BasePresenter) {
        this.presenter = presenter as T
    }

    fun getPresenter(): T {
        return presenter;
    }

    override fun showLoading() {
        if (progressContainer != null) {
            progressContainer!!.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (progressContainer != null) {
            progressContainer!!.visibility = View.GONE
        }
    }

}