package com.kapirti.eagle.data.injection

import android.content.Context
import com.kapirti.eagle.data.repository.SettingRepository
import com.kapirti.eagle.data.repository.local.OnBoardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOnBoardingRepository(
        @ApplicationContext context: Context
    ) = OnBoardingRepository(context = context)

    @Provides
    @Singleton
    fun provideSettingRepository(
        @ApplicationContext context: Context
    ) = SettingRepository(context = context)

}