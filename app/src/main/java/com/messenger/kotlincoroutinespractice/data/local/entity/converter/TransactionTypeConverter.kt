package com.messenger.kotlincoroutinespractice.data.local.entity.converter

import androidx.room.TypeConverter
import com.messenger.kotlincoroutinespractice.data.local.entity.transaction.TransactionTypeEntity


class TransactionTypeConverter {
    @TypeConverter
    fun fromTransactionType(type: TransactionTypeEntity): String {
        return type.name
    }

    @TypeConverter
    fun toTransactionType(value: String): TransactionTypeEntity {
        return TransactionTypeEntity.valueOf(value)
    }
}