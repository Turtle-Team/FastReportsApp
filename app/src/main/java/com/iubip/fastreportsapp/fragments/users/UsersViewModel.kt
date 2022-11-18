package com.iubip.fastreportsapp.fragments.users

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
class UsersViewModel @Inject constructor(private val fastReportRepository: FastReportRepository) :
    ViewModel() {
    private val _groups= MutableLiveData<ContentGroup>()
    val groups: LiveData<ContentGroup> = _groups

    fun getContentGroups() = viewModelScope.launch(Dispatchers.IO){
        _groups.postValue(fastReportRepository.getContentGroups())
    }
}