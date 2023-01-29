package com.stellarworker.learninghelper.repository

import com.stellarworker.learninghelper.domain.ClassesInfo

interface ClassesRep {
    fun getClassesInfo(): ClassesInfo
}