package com.iubip.fastreportsapp.fragments.exports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.fragments.BaseItemType
import com.iubip.fastreportsapp.model.ContentExport
import com.iubip.fastreportsapp.model.ContentReport
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExportsViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _exports= MutableLiveData<List<BaseItemType>>()
    val exports: LiveData<List<BaseItemType>> = _exports

    fun getContentExport() = viewModelScope.launch(Dispatchers.IO) {
        val list = fastReportRepository.getContentExports().files
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
        _exports.postValue(list2)
    }
}