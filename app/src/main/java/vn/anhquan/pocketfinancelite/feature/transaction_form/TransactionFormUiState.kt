package vn.anhquan.pocketfinancelite.feature.transaction_form

import vn.anhquan.pocketfinancelite.domain.model.TransactionType

data class TransactionFormUiState(
    val id: Long = 0,
    val amount: String = "",
    val type: TransactionType = TransactionType.EXPENSE,
    val category: String = "",
    val note: String = "",
    val occurredAtEpochMillis: Long = System.currentTimeMillis(),
    val amountError: String? = null,
    val categoryError: String? = null,
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
)
