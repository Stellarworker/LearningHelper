package com.stellarworker.learninghelper.ui.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.stellarworker.learninghelper.data.SingleLiveEvent
import com.stellarworker.learninghelper.domain.ClassesFragmentMessage
import com.stellarworker.learninghelper.repository.ClassesRep

class ClassesFragmentViewModel(
    private val repository: ClassesRep
) : ViewModel() {
    private val _messagesLiveData = SingleLiveEvent<ClassesFragmentMessage>()
    val messagesLiveData: LiveData<ClassesFragmentMessage> by this::_messagesLiveData

    fun requestClassesInfo() {
        _messagesLiveData.postValue(ClassesFragmentMessage.ClassesMessage(repository.getClassesInfo().classes))
    }
}