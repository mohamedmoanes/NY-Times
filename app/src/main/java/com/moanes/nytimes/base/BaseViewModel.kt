package com.moanes.nytimes.base

import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    // Coroutine's background job
    private val job = Job()

    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    val showLoading = MutableLiveData<Boolean>()
    val showNoData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        // Clear our job when the linked activity is destroyed to avoid memory leaks
        job.cancel()
    }

    fun <T> callRequestLiveData(request: suspend () -> T) = liveData(Dispatchers.Main) {
        try {
            showLoading.postValue(true)
            val result = withContext(Dispatchers.IO) {
                request()
            }
            emit(result)

            showLoading.postValue(false)

        } catch (exception: Exception) {
            showLoading.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }
    }

    fun <T> handelRequestLiveData(block: suspend LiveDataScope<T>.() -> Unit) =
        liveData(Dispatchers.Main) {
            try {
                showLoading.postValue(true)
                block()
                showLoading.postValue(false)

            } catch (exception: Exception) {
                showLoading.postValue(false)
                errorLiveData.postValue(exception.localizedMessage)
            }
        }
}