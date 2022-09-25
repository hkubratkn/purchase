package com.kapirti.eagle.data.injection

import android.content.Context
import com.kapirti.eagle.data.repository.HomeScreenRepository
import com.kapirti.eagle.data.repository.local.OnBoardingDataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideOnBoardingDataStoreRepository(
        @ApplicationContext context: Context
    ) = OnBoardingDataStoreRepository(context = context)

    @Provides
    @Singleton
    fun provideHomeScreenRepository(
        @ApplicationContext context: Context
    ) = HomeScreenRepository(context = context)

}