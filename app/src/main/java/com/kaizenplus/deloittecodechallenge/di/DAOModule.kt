package com.kaizenplus.deloittecodechallenge.di

import com.hazem.datalayer.cache.dao.UserDao
import com.hazem.datalayer.cache.dao.UserDaoImpl
import com.hazem.datalayer.cache.entity.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DAOModule {
    @Singleton
    @Provides
    fun provideUserDAO(boxStore: Box<UserEntity>?): UserDao {
        return UserDaoImpl(boxStore)
    }

}