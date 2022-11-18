package com.iubip.fastreportsapp.fragments.exports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _exports= MutableLiveData<ContentExport>()
    val exports: LiveData<ContentExport> = _exports

    fun getContentExport() = viewModelScope.launch(Dispatchers.IO){
        _exports.postValue(fastReportRepository.getContentExports())
    }
}