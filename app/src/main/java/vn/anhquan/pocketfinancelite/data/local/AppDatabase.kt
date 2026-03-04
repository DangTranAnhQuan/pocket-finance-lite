package vn.anhquan.pocketfinancelite.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.anhquan.pocketfinancelite.data.local.dao.TransactionDao
import vn.anhquan.pocketfinancelite.data.local.entity.TransactionEntity

@Database(
    entities = [TransactionEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
