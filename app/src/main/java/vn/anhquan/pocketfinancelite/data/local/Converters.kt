package vn.anhquan.pocketfinancelite.data.local

import androidx.room.TypeConverter
import vn.anhquan.pocketfinancelite.domain.model.TransactionType

class Converters {
    @TypeConverter
    fun fromTransactionType(type: TransactionType): String = type.name

    @TypeConverter
    fun toTransactionType(value: String): TransactionType = TransactionType.valueOf(value)
}
