package ru.faaen.hackapp.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.faaen.hackapp.App

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(): Context = App.context
}