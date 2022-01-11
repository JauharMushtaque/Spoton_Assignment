package com.example.spotonassignment

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotonassignment.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@InternalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private var _dataList : MutableLiveData<List<Data>> = MutableLiveData<List<Data>>()
    init {
        startRepeatingJob(10000)
    }

    fun getData(): LiveData<List<Data>>{
        return _dataList
    }

    private fun getDataFromRepository(){
        _dataList.value = mainRepository.getUpdatedCrypto()

    }


    /**
     * start Job
     * val job = startRepeatingJob()
     * cancels the job and waits for its completion
     * job.cancelAndJoin()
     * Params
     * timeInterval: time milliSeconds
     */
    @InternalCoroutinesApi
    fun startRepeatingJob(timeInterval: Long): Job {
        return viewModelScope/*(Dispatchers.Default)*/.launch {
            while (NonCancellable.isActive) {
                getDataFromRepository()
                delay(timeInterval)
            }
        }
    }

}