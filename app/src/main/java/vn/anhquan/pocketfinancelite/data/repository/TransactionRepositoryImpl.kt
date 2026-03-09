package vn.anhquan.pocketfinancelite.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import vn.anhquan.pocketfinancelite.data.local.dao.TransactionDao
import vn.anhquan.pocketfinancelite.data.local.entity.TransactionEntity
import vn.anhquan.pocketfinancelite.domain.model.Transaction
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository

class TransactionRepositoryImpl @Inject constructor(
    private val dao: TransactionDao,
) : TransactionRepository {

    override fun observeByRange(from: Long, to: Long): Flow<List<Transaction>> =
        dao.observeByRange(from, to).map { list -> list.map { it.toDomain() } }

    override suspend fun upsert(tx: Transaction): Long =
        dao.upsert(tx.toEntity())

    override suspend fun delete(tx: Transaction) =
        dao.delete(tx.toEntity())

    override suspend fun getById(id: Long): Transaction? =
        dao.getById(id)?.toDomain()
}

private fun TransactionEntity.toDomain() = Transaction(
    id = id,
    amount = amount,
    type = type,
    category = category,
    note = note,
    occurredAtEpochMillis = occurredAtEpochMillis,
    createdAtEpochMillis = createdAtEpochMillis,
)

private fun Transaction.toEntity() = TransactionEntity(
    id = id,
    amount = amount,
    type = type,
    category = category,
    note = note,
    occurredAtEpochMillis = occurredAtEpochMillis,
    createdAtEpochMillis = createdAtEpochMillis,
)