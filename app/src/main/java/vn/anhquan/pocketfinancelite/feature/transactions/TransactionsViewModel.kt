package vn.anhquan.pocketfinancelite.feature.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import vn.anhquan.pocketfinancelite.domain.usecase.DeleteTransactionUseCase
import vn.anhquan.pocketfinancelite.domain.usecase.GetTransactionsByRangeUseCase

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getByRange: GetTransactionsByRangeUseCase,
    private val deleteTx: DeleteTransactionUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionsUiState())
    val uiState: StateFlow<TransactionsUiState> = _uiState.asStateFlow()

    private val from = 0L
    private val to = Long.MAX_VALUE

    fun onEvent(event: TransactionsUiEvent) {
        when (event) {
            TransactionsUiEvent.OnScreenShown,
            TransactionsUiEvent.OnRetry -> observeTransactions()

            is TransactionsUiEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    deleteTx(event.tx)
                }
            }
        }
    }

    private fun observeTransactions() {
        viewModelScope.launch {
            getByRange(from, to)
                .onStart {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        errorMessage = null
                    )
                }
                .catch { e ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "Unknown error"
                    )
                }
                .collect { list ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        items = list,
                        errorMessage = null
                    )
                }
        }
    }
}
