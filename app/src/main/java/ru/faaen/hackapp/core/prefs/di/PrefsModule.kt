package ru.faaen.hackapp.core.prefs.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.faaen.hackapp.App
import ru.faaen.hackapp.R

@Module
@InstallIn(SingletonComponent::class)
class PrefsModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            App.context.getString(R.string.settings_prefs_filename),
            Context.MODE_PRIVATE
        )
    }
}