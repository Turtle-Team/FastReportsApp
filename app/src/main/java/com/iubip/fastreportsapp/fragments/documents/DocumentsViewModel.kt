package com.iubip.fastreportsapp.fragments.documents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.model.Folder
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DocumentsViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {

    private val _response = MutableLiveData<Folder>()
    val response: LiveData<Folder> = _response

    fun getFolder() = viewModelScope.launch(Dispatchers.IO) {
        _response.postValue(fastReportRepository.getwtf())
    }
}