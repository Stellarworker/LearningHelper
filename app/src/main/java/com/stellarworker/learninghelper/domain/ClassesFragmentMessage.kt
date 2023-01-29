package com.stellarworker.learninghelper.domain

sealed class ClassesFragmentMessage {
    data class ClassesMessage(val classes: List<ClassesEx>) : ClassesFragmentMessage()
    data class InfoSnackBar(val text: String) : ClassesFragmentMessage()
    data class InfoToast(val text: String, val length: Int) : ClassesFragmentMessage()
}