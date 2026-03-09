package vn.anhquan.pocketfinancelite.feature.transactions

import vn.anhquan.pocketfinancelite.domain.model.Transaction

data class TransactionsUiState(
    val isLoading: Boolean = true,
    val items: List<Transaction> = emptyList(),
    val errorMessage: String? = null,
)
