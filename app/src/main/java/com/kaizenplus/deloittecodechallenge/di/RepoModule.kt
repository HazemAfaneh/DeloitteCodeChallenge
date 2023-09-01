package com.kaizenplus.deloittecodechallenge.di

import com.hazem.corelayer.repo.UserAuthenticationRepo
import com.hazem.datalayer.repoImpl.UserAuthenticationRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepoModule {
    //UserAuthenticationRepo

    @Singleton
    @Provides
    fun provideUserAuthenticationRepo(
    ): UserAuthenticationRepo = UserAuthenticationRepoImpl()
}