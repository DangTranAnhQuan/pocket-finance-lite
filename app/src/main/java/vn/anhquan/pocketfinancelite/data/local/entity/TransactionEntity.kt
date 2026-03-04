package vn.anhquan.pocketfinancelite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Long,                 // lưu VND theo đơn vị nhỏ nhất
    val type: String,                 // "INCOME" | "EXPENSE" (tạm)
    val category: String,             // "FOOD", "TRANSPORT", ...
    val note: String?,
    val occurredAtEpochMillis: Long,  // thời gian phát sinh
    val createdAtEpochMillis: Long = System.currentTimeMillis(),
)