package com.messenger.kotlincoroutinespractice.di

import android.content.Context
import androidx.room.Room
import com.messenger.kotlincoroutinespractice.data.local.dao.CategoryDao
import com.messenger.kotlincoroutinespractice.data.local.dao.TransactionDao
import com.messenger.kotlincoroutinespractice.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "expense_analyzer.db"
        ).build()
    }

    @Provides
    fun provideCategoryDao(
        database: AppDatabase
    ): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideTransactionDao(
        database: AppDatabase
    ): TransactionDao {
        return database.transactionDao()
    }
}