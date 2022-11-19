package com.iubip.fastreportsapp.alerts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.model.ContentFolder
import com.iubip.fastreportsapp.model.ContentReport
import com.iubip.fastreportsapp.model.File
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderExportViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _response = MutableLiveData<ContentReport>()
    val response: LiveData<ContentReport> = _response

    fun getFolders() = viewModelScope.launch(Dispatchers.IO) {
            _response.postValue(fastReportRepository.getContentReports())
        }
    }