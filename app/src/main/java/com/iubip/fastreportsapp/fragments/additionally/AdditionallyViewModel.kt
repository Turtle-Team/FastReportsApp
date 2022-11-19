package com.iubip.fastreportsapp.fragments.additionally

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.model.ContentGroup
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdditionallyViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _download= MutableLiveData<String>()
    val download: LiveData<String> = _download

//    fun getContentGroups() = viewModelScope.launch(Dispatchers.IO){
//        _groups.postValue(fastReportRepository.getContentGroups())
//    }

        fun  downloadTemplateFile() = viewModelScope.launch(Dispatchers.IO){
            _download.postValue(fastReportRepository.downloadTemplateFile())

        }
}