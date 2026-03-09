package vn.anhquan.pocketfinancelite.feature.transaction_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vn.anhquan.pocketfinancelite.domain.model.TransactionType

@Composable
fun TransactionFormScreen(
    transactionId: Long? = null,
    onBack: () -> Unit,
    viewModel: TransactionFormViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(TransactionFormUiEvent.OnScreenLoad(transactionId))
    }

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) onBack()
    }

    TransactionFormContent(
        state = state,
        onEvent = viewModel::onEvent,
        onBack = onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TransactionFormContent(
    state: TransactionFormUiState,
    onEvent: (TransactionFormUiEvent) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.id == 0L) "Add Transaction" else "Edit Transaction") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Amount
            OutlinedTextField(
                value = state.amount,
                onValueChange = { onEvent(TransactionFormUiEvent.OnAmountChange(it)) },
                label = { Text("Amount") },
                isError = state.amountError != null,
                supportingText = { state.amountError?.let { Text(it) } },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            // Type: Income / Expense
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TransactionType.entries.forEach { type ->
                    FilterChip(
                        selected = state.type == type,
                        onClick = { onEvent(TransactionFormUiEvent.OnTypeChange(type)) },
                        label = { Text(type.name) }
                    )
                }
            }

            // Category
            OutlinedTextField(
                value = state.category,
                onValueChange = { onEvent(TransactionFormUiEvent.OnCategoryChange(it)) },
                label = { Text("Category") },
                isError = state.categoryError != null,
                supportingText = { state.categoryError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            // Note
            OutlinedTextField(
                value = state.note,
                onValueChange = { onEvent(TransactionFormUiEvent.OnNoteChange(it)) },
                label = { Text("Note (optional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onEvent(TransactionFormUiEvent.OnSaveClick) },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Save")
                }
            }
        }
    }
}
