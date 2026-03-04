package vn.anhquan.pocketfinancelite.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import vn.anhquan.pocketfinancelite.data.local.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Query("""
        SELECT * FROM transactions
        WHERE occurredAtEpochMillis BETWEEN :from AND :to
        ORDER BY occurredAtEpochMillis DESC
    """)
    fun observeByRange(from: Long, to: Long): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: TransactionEntity): Long

    @Delete
    suspend fun delete(entity: TransactionEntity)

    @Query("SELECT * FROM transactions WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): TransactionEntity?
}
