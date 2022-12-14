package com.iubip.fastreportsapp.alerts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.model.ContentExport
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderExportViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _response = MutableLiveData<ContentExport>()
    val response: LiveData<ContentExport> = _response

    var idFolder = ""
    var nameFolder = ""

    fun getFolders() = viewModelScope.launch(Dispatchers.IO) {
        _response.postValue(fastReportRepository.getContentExports())
    }

    fun getFolderById(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _response.postValue(fastReportRepository.getFolderExportsByid(id))
    }

    fun exportInThisFolder(id: String, folder: String) =
        viewModelScope.launch { fastReportRepository.toExportFile(id, folder) }
}