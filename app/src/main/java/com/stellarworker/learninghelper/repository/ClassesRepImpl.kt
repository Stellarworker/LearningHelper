package com.stellarworker.learninghelper.repository

import com.stellarworker.learninghelper.domain.ClassesEx
import com.stellarworker.learninghelper.domain.ClassesInfo
import com.stellarworker.learninghelper.domain.Homework
import com.stellarworker.learninghelper.utils.EMPTY
import com.stellarworker.learninghelper.utils.ZERO
import com.stellarworker.learninghelper.utils.convertDateToLong

class ClassesRepImpl : ClassesRep {
    override fun getClassesInfo(): ClassesInfo {
        val datePattern = DATE_PATTERN
        val newExamsDate = convertDateToLong(EXAMS_DATE, datePattern)
        val classesList = listOf(
            ClassesEx(
                title = "History",
                icon = Int.ZERO,
                timeBegin = "8.00",
                timeEnd = "8.45",
                teacher = "Mrs. Thomas",
                description = String.EMPTY,
                openInSkype = true
            ),
            ClassesEx(
                title = "Literature",
                icon = Int.ZERO,
                timeBegin = "9.00",
                timeEnd = "9.45",
                teacher = "Mrs. Barros",
                description = String.EMPTY,
                openInSkype = false
            ),
            ClassesEx(
                title = "Physical Education",
                icon = Int.ZERO,
                timeBegin = "10.00",
                timeEnd = "11.35",
                teacher = "Mr. Barros",
                description = "Intensive preparation for The Junior World Championship in Los Angeles",
                openInSkype = false
            ),
        )
        val homeworkList = listOf(
            Homework(
                title = "Literature",
                icon = 0,
                deadline = convertDateToLong("2023.01.29.12.00", datePattern),
                description = "Learn scenes 1.1-1.12 of The Master and Margarita."
            ),
            Homework(
                title = "Physics",
                icon = 0,
                deadline = convertDateToLong("2023.02.03.12.00", datePattern),
                description = "Learn Newtons's law of motion"
            )
        )
        return ClassesInfo(
            examsDate = newExamsDate,
            classes = classesList,
            homeworks = homeworkList
        )
    }

    companion object {
        private const val DATE_PATTERN = "yyyy.MM.dd.HH.mm"
        private const val EXAMS_DATE = "2023.05.17.10.00"
    }
}