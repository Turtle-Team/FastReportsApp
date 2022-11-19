package com.iubip.fastreportsapp.fragments.additionally

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.model.ContentGroup
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdditionallyViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    fun downloadTemplateFile() = viewModelScope.launch(Dispatchers.IO) {
        _response.postValue(fastReportRepository.downloadTemplateFile())
    }
}