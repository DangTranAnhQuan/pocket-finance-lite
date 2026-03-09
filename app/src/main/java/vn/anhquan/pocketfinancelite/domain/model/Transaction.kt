package vn.anhquan.pocketfinancelite.domain.model

data class Transaction(
    val id: Long,
    val amount: Double,
    val type: TransactionType,
    val category: String,
    val note: String? = null,
    val occurredAtEpochMillis: Long,
    val createdAtEpochMillis: Long = System.currentTimeMillis(),
)
