package com.stellarworker.learninghelper.domain

sealed class AppMessage {
    data class ClassesMessage(val classesInfo: ClassesInfo) : AppMessage()
    data class InfoSnackBar(val text: String) : AppMessage()
    data class InfoToast(val text: String, val length: Int) : AppMessage()
}
