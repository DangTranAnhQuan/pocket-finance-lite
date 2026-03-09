package vn.anhquan.pocketfinancelite.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vn.anhquan.pocketfinancelite.data.local.dao.TransactionDao
import vn.anhquan.pocketfinancelite.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}

