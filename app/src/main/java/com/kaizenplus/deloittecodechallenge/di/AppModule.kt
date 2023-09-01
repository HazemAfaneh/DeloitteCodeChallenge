package com.kaizenplus.deloittecodechallenge.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule  {

    @Singleton
    @Provides
    fun provideSharedPref(@ApplicationContext applicationContext: Context): SharedPreferences? {
        return applicationContext.getSharedPreferences("OR_PREF", Context.MODE_PRIVATE)
    }


}