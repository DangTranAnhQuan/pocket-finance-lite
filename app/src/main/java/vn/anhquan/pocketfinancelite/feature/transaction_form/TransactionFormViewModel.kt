package vn.anhquan.pocketfinancelite.feature.transaction_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vn.anhquan.pocketfinancelite.domain.model.Transaction
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class TransactionFormViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionFormUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: TransactionFormUiEvent) {
        when (event) {
            is TransactionFormUiEvent.OnScreenLoad -> loadTransaction(event.transactionId)
            is TransactionFormUiEvent.OnAmountChange ->
                _uiState.update { it.copy(amount = event.value, amountError = null) }
            is TransactionFormUiEvent.OnTypeChange ->
                _uiState.update { it.copy(type = event.value) }
            is TransactionFormUiEvent.OnCategoryChange ->
                _uiState.update { it.copy(category = event.value, categoryError = null) }
            is TransactionFormUiEvent.OnNoteChange ->
                _uiState.update { it.copy(note = event.value) }
            is TransactionFormUiEvent.OnDateChange ->
                _uiState.update { it.copy(occurredAtEpochMillis = event.epochMillis) }
            TransactionFormUiEvent.OnSaveClick -> save()
        }
    }

    private fun loadTransaction(id: Long?) {
        if (id == null || id == 0L) return
        viewModelScope.launch {
            val tx = repository.getById(id) ?: return@launch
            _uiState.update {
                it.copy(
                    id = tx.id,
                    amount = tx.amount.toString(),
                    type = tx.type,
                    category = tx.category,
                    note = tx.note ?: "",
                    occurredAtEpochMillis = tx.occurredAtEpochMillis,
                )
            }
        }
    }

    private fun save() {
        val state = _uiState.value
        var hasError = false

        if (state.amount.isBlank() || state.amount.toDoubleOrNull() == null) {
            _uiState.update { it.copy(amountError = "Amount is required") }
            hasError = true
        }
        if (state.category.isBlank()) {
            _uiState.update { it.copy(categoryError = "Category is required") }
            hasError = true
        }
        if (hasError) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.upsert(
                Transaction(
                    id = state.id,
                    amount = state.amount.toDouble(),
                    type = state.type,
                    category = state.category,
                    note = state.note.ifBlank { null },
                    occurredAtEpochMillis = state.occurredAtEpochMillis,
                )
            )
            _uiState.update { it.copy(isLoading = false, isSaved = true) }
        }
    }
}
