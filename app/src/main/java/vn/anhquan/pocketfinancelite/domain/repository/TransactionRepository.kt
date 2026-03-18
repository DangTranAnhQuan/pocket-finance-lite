package vn.anhquan.pocketfinancelite.domain.repository

import kotlinx.coroutines.flow.Flow
import vn.anhquan.pocketfinancelite.domain.model.MonthlySummary
import vn.anhquan.pocketfinancelite.domain.model.Transaction

interface TransactionRepository {
    fun observeByRange(from: Long, to: Long): Flow<List<Transaction>>
    fun observeMonthlySummary(from: Long, to: Long): Flow<List<MonthlySummary>>
    suspend fun upsert(tx: Transaction): Long
    suspend fun delete(tx: Transaction)
    suspend fun getById(id: Long): Transaction?
}