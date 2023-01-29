package com.stellarworker.learninghelper.domain

import com.stellarworker.learninghelper.utils.EMPTY

data class ClassesEx(
    val title: String,
    val icon: Int,
    val timeBegin: String,
    val timeEnd: String,
    val teacher: String = String.EMPTY,
    val description: String = String.EMPTY,
    val openInSkype: Boolean = false
)