package com.iubip.fastreportsapp.alerts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iubip.fastreportsapp.repository.FastReportRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RenameViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {

    fun renameFile(item: String) = viewModelScope.launch(Dispatchers.IO) {
        fastReportRepository.renameFileTemplate(item)
    }
}