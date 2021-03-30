package com.moanes.nytimes.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.moanes.nytimes.utils.views.ProgressDialog

abstract class BaseActivity : AppCompatActivity() {
    private var loadingDialog: ProgressDialog? = null

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        loadingDialog = ProgressDialog(this)
        loadingDialog?.setOnCancelListener {
//            findNavController().navigateUp()
            try {
                onBackPressed()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        init()
    }

    open fun showLoading() {
        loadingDialog?.let {
            it.show()
        }
    }

    open fun hideLoading() {
        loadingDialog?.let { it.dismiss() }
    }

    open fun showErrorToast(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    open fun showErrorToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onPause() {
        super.onPause()
        if (loadingDialog != null && loadingDialog?.isShowing!!) {
            loadingDialog?.dismiss()
        }
    }

    override fun onStop() {
        super.onStop()
        loadingDialog = null
    }

    fun handleProgress(viewModel: BaseViewModel, swipeRefreshLayout: SwipeRefreshLayout? = null) {
        viewModel.showLoading.observe(this, {

            swipeRefreshLayout?.let { swipeRefreshLayout ->
                swipeRefreshLayout.isRefreshing = it
            }

            if (it)
                showLoading()
            else
                hideLoading()
        })
    }

    fun handleError(viewModel: BaseViewModel) {
        viewModel.errorLiveData.observe(this, {
            showErrorToast(it)
        })
    }

     fun handleNoData(viewModel: BaseViewModel,noData:View) {
        viewModel.showNoData.observe(this, {
            if (it)
                noData.visibility = View.VISIBLE
            else
                noData.visibility = View.GONE
        })
    }
}