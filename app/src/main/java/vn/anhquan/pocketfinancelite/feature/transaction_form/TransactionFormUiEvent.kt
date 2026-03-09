package vn.anhquan.pocketfinancelite.feature.transaction_form

import vn.anhquan.pocketfinancelite.domain.model.TransactionType

sealed interface TransactionFormUiEvent {
    data class OnAmountChange(val value: String) : TransactionFormUiEvent
    data class OnTypeChange(val value: TransactionType) : TransactionFormUiEvent
    data class OnCategoryChange(val value: String) : TransactionFormUiEvent
    data class OnNoteChange(val value: String) : TransactionFormUiEvent
    data class OnDateChange(val epochMillis: Long) : TransactionFormUiEvent
    data object OnSaveClick : TransactionFormUiEvent
    data class OnScreenLoad(val transactionId: Long?) : TransactionFormUiEvent
}
