package com.kaizenplus.deloittecodechallenge.di

import android.app.Application
import com.hazem.datalayer.cache.entity.MyObjectBox
import com.hazem.datalayer.cache.entity.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class BoxStoreModule {

    @Singleton
    @Provides
    fun provideUserBox(boxStore: BoxStore): Box<UserEntity>? {
        return boxStore.boxFor(UserEntity::class.java)
    }
    @Singleton
    @Provides
    fun provideBoxStore(application: Application): BoxStore {
        return MyObjectBox.builder()
            .androidContext(application)
            .build()
    }
}