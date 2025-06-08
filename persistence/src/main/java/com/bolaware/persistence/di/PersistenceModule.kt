package com.bolaware.persistence.di

import android.content.Context
import androidx.room.Room
import com.bolaware.persistence.AppDatabase
import com.bolaware.persistence.ExampleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
    }

    @Provides
    @Singleton
    fun provideExampleDao(database: AppDatabase): ExampleDao {
        return database.exampleDao()
    }
}