package com.stellarworker.learninghelper.domain

data class ClassesInfo(
    val examsDate: Long,
    val classes: List<ClassesEx>,
    val homeworks: List<Homework>
)
