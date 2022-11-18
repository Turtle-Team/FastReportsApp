package com.iubip.fastreportsapp.fragments.reports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _reports = MutableLiveData<ContentReport>()
    val reports: LiveData<ContentReport> = _reports

    fun getContentReport() = viewModelScope.launch(Dispatchers.IO){
        _reports.postValue(fastReportRepository.getContentReports())
    }
}