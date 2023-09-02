package com.kaizenplus.deloittecodechallenge.di

import com.hazem.corelayer.login.LoginUseCase
import com.hazem.corelayer.login.LoginUseCaseImpl
import com.hazem.corelayer.register.RegisterUserCase
import com.hazem.corelayer.register.RegisterUserCaseImpl
import com.hazem.corelayer.repo.LoginRepo
import com.hazem.corelayer.repo.RegisterRepo
import com.hazem.corelayer.repo.UserAuthenticationRepo
import com.hazem.corelayer.repo.UserRepo
import com.hazem.corelayer.usecase.UserAuthenticationUseCase
import com.hazem.corelayer.usecase.UserAuthenticationUseCaseImpl
import com.hazem.corelayer.user.LogoutUserUseCase
import com.hazem.corelayer.user.LogoutUserUseCaseImpl
import com.hazem.corelayer.user.UserUseCase
import com.hazem.corelayer.user.UserUseCaseImpl
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

    @Singleton
    @Provides
    fun provideUserUseCase( repo: UserRepo): UserUseCase =
        UserUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideLoginUseCase( repo: LoginRepo): LoginUseCase =
        LoginUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideLogoutUserUseCase( repo: UserRepo): LogoutUserUseCase =
        LogoutUserUseCaseImpl(repo)

    @Singleton
    @Provides
    fun provideRegisterUserCaseUseCase( repo: RegisterRepo): RegisterUserCase =
        RegisterUserCaseImpl(repo)

}