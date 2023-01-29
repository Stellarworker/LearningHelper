package com.stellarworker.learninghelper.di

import com.stellarworker.learninghelper.repository.ClassesRep
import com.stellarworker.learninghelper.repository.ClassesRepImpl
import com.stellarworker.learninghelper.ui.classes.ClassesFragmentViewModel
import com.stellarworker.learninghelper.ui.home.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    single<ClassesRep> { ClassesRepImpl() }
    viewModel { MainFragmentViewModel(get()) }
    viewModel { ClassesFragmentViewModel(get()) }
}