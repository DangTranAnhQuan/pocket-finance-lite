package vn.anhquan.pocketfinancelite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import vn.anhquan.pocketfinancelite.domain.model.TransactionType

@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,                 // lưu VND theo đơn vị nhỏ nhất
    val type: TransactionType,                 // "INCOME" | "EXPENSE"
    val category: String,             // "FOOD", "TRANSPORT", ...
    val note: String?,
    val occurredAtEpochMillis: Long,  // thời gian phát sinh
    val createdAtEpochMillis: Long = System.currentTimeMillis(),
)