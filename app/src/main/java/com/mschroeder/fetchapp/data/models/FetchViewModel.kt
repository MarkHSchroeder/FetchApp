package com.mschroeder.fetchapp.data.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mschroeder.fetchapp.data.FetchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FetchViewModel @Inject constructor(
    private val fetchRepository: FetchRepository
) : ViewModel() {
    // List of items
    private val _listData = MutableLiveData<List<FetchResponseModel>>()
    val listData: LiveData<List<FetchResponseModel>> get() = _listData

    // Controls showing loading
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    // Controls showing if we receive an error
    private val _error = MutableLiveData<Exception?>()
    val error: LiveData<Exception?> get() = _error

    fun getList() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = fetchRepository.getList()
                _listData.value = response
            } catch (e: Exception) {
                _error.value = e
            } finally {
                // Always turn off loading
                _loading.value = false
            }
        }
    }
}