package com.test.kumparan.di

import com.test.core.domain.usecase.ContentInteractor
import com.test.core.domain.usecase.ContentUseCase
import com.test.kumparan.viewmodel.PostViewModel
import com.test.kumparan.viewmodel.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ContentUseCase> { ContentInteractor(get()) }
}

val viewModelModule = module {
    viewModel { PostViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}