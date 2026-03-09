package vn.anhquan.pocketfinancelite.feature.transactions

import vn.anhquan.pocketfinancelite.domain.model.Transaction


sealed interface TransactionsUiEvent {
    data object OnScreenShown : TransactionsUiEvent
    data object OnRetry : TransactionsUiEvent
    data class  OnDeleteClick (val tx: Transaction) : TransactionsUiEvent
}