package com.kaizenplus.deloittecodechallenge.di

import android.accounts.AccountManager
import android.content.Context
import com.hazem.corelayer.repo.LoginRepo
import com.hazem.corelayer.repo.RegisterRepo
import com.hazem.corelayer.repo.UserAuthenticationRepo
import com.hazem.datalayer.cache.dao.UserDaoImpl
import com.hazem.datalayer.repoImpl.Authenticator
import com.hazem.datalayer.repoImpl.LoginRepoImpl
import com.hazem.datalayer.repoImpl.RegisterRepoImpl
import com.hazem.datalayer.repoImpl.UserAuthenticationRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideLoginRepo(authenticator:Authenticator
    ): LoginRepo = LoginRepoImpl(authenticator)

    @Singleton
    @Provides
    fun provideRegisterRepoRepo(
        daoImpl: UserDaoImpl,
        authenticator:Authenticator
    ): RegisterRepo = RegisterRepoImpl(daoImpl, authenticator)

    @Singleton
    @Provides
    fun provideAuthenticator(mAccountManager: AccountManager): Authenticator =
        Authenticator(mAccountManager)

    @Singleton
    @Provides
    fun provideAccountManager(@ApplicationContext context: Context): AccountManager =
        AccountManager.get(context)
}