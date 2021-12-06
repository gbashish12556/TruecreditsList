package com.example.truecreditslist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truecreditslist.data.TruecreditsPost
import com.example.truecreditslist.repository.TruecreditPostRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job


class MainViewModel(private val truecreditsPostRepo: TruecreditPostRepo) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _isDataLoadingError = MutableLiveData<Boolean>(false)
    val isDataLoadingError = _isDataLoadingError

    private val _items = MutableLiveData<List<TruecreditsPost>>()
    val items: LiveData<List<TruecreditsPost>> = _items


    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        noMatchFound()
    }

    fun noMatchFound(){
        _items.postValue(emptyList())
        _isDataLoadingError.postValue(true)
    }

}