package vn.anhquan.pocketfinancelite.domain.model

data class Transaction(
    val id: Long,
    val amount: Long,
    val type: String,
    val category: String,
    val note: String?,
    val occurredAtEpochMillis: Long,
)
