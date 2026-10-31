package com.messenger.kotlincoroutinespractice.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.messenger.kotlincoroutinespractice.data.local.dao.CategoryDao
import com.messenger.kotlincoroutinespractice.data.local.dao.TransactionDao
import com.messenger.kotlincoroutinespractice.data.local.entity.category.CategoryEntity
import com.messenger.kotlincoroutinespractice.data.local.entity.converter.TransactionTypeConverter
import com.messenger.kotlincoroutinespractice.data.local.entity.transaction.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(TransactionTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
}