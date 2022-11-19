package com.iubip.fastreportsapp.fragments.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.model.ContentFolder
import com.iubip.fastreportsapp.model.ContentReport
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportsViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _reports = MutableLiveData<List<BaseItemType>>()
    val reports: LiveData<List<BaseItemType>> = _reports

    fun getContentReport() = viewModelScope.launch(Dispatchers.IO) {
        val list = fastReportRepository.getContentReports().files
        val list2 = mutableListOf<BaseItemType>()

        list.map {
            if (it.type == "File") {
                list2.add(
                    BaseItemType.Folder(
                    createdTime = it.createdTime,
                    editedTime = it.editedTime,
                    id = it.id,
                    name = it.name,
                    size = it.size,
                    status = it.status,
                    statusReason = it.statusReason,
                    type = it.type
                ))
            } else {
                list2.add(
                    BaseItemType.File(
                    createdTime = it.createdTime,
                    editedTime = it.editedTime,
                    id = it.id,
                    name = it.name,
                    size = it.size,
                    status = it.status,
                    statusReason = it.statusReason,
                    type = it.type
                ))
            }
        }
        _reports.postValue(list2)
    }

    fun deleteFolder(item: String) = viewModelScope.launch(Dispatchers.IO) {
        fastReportRepository.deleteFolderReport(item)
    }

    fun deleteFile(item: String) = viewModelScope.launch(Dispatchers.IO) {
        fastReportRepository.deleteFileReport(item)
    }

    fun renameFile(item: String, name: String) = viewModelScope.launch(Dispatchers.IO) {
        fastReportRepository.renameFileReport(item, name)
    }
}