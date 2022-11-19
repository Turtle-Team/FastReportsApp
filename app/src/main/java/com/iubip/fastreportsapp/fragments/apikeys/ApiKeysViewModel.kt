package com.iubip.fastreportsapp.fragments.apikeys

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.model.ApiKey
import com.iubip.fastreportsapp.model.ApiKeys
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiKeysViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _apikeys= MutableLiveData<List<ApiKey>>()
    val apikeys: LiveData<List<ApiKey>> = _apikeys

    fun getApiKeys() = viewModelScope.launch(Dispatchers.IO){
        _apikeys.postValue(listOf(fastReportRepository.getApiKeys()))
    }
}