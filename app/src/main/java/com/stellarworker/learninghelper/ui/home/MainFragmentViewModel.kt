package com.stellarworker.learninghelper.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stellarworker.learninghelper.data.SingleLiveEvent
import com.stellarworker.learninghelper.domain.AppMessage
import com.stellarworker.learninghelper.repository.ClassesRep

class MainFragmentViewModel(
    private val repository: ClassesRep
) : ViewModel() {
    private val _messagesLiveData = SingleLiveEvent<AppMessage>()
    val messagesLiveData: LiveData<AppMessage> by this::_messagesLiveData

    fun requestClassesInfo() {
        _messagesLiveData.postValue(AppMessage.ClassesMessage(repository.getClassesInfo()))
    }

}