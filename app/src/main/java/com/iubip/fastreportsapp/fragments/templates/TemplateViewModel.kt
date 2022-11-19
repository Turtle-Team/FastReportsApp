package com.iubip.fastreportsapp.fragments.templates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _response = MutableLiveData<List<BaseItemType>>()
    val response: LiveData<List<BaseItemType>> = _response

    fun getContentFolder() = viewModelScope.launch(Dispatchers.IO) {
        val list = fastReportRepository.getContentFolder().files
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
                    )
                )
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
                    )
                )
            }
        }
        _response.postValue(list2)
    }

    fun deleteFolder(item: String) = viewModelScope.launch(Dispatchers.IO) {
        fastReportRepository.deleteFolderTemplate(item)
    }

    fun deleteFile(item: String) = viewModelScope.launch(Dispatchers.IO) {
        fastReportRepository.deleteFileTemplate(item)
    }

    fun createFolder(name: String) = viewModelScope.launch(Dispatchers.IO){
        fastReportRepository.createFolderTemplate(name)
    }
}
