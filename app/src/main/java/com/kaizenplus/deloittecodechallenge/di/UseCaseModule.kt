package com.kaizenplus.deloittecodechallenge.di

import com.hazem.corelayer.repo.UserAuthenticationRepo
import com.hazem.corelayer.usecase.UserAuthenticationUseCase
import com.hazem.corelayer.usecase.UserAuthenticationUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
    @Singleton
    @Provides
    fun provideUserAuthenticationUseCase( repo: UserAuthenticationRepo): UserAuthenticationUseCase =
        UserAuthenticationUseCaseImpl(repo)

}