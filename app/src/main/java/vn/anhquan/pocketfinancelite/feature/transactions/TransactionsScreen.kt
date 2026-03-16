package vn.anhquan.pocketfinancelite.feature.transactions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import vn.anhquan.pocketfinancelite.domain.model.Transaction

@Composable
fun TransactionsScreenRoot(
    onAddClick: () -> Unit,
    onEditClick: (Long) -> Unit,
    viewModel: TransactionsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(TransactionsUiEvent.OnScreenShown)
    }

    TransactionsContent(
        state = state,
        onEvent = viewModel::onEvent,
        onAddClick = onAddClick,
        onTransactionClick = onEditClick
    )
}

@Composable
private fun TransactionsContent(
    state: TransactionsUiState,
    onEvent: (TransactionsUiEvent) -> Unit,
    onAddClick: () -> Unit,
    onTransactionClick: (Long) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add transaction"
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.errorMessage != null -> {
                    ErrorView(
                        message = state.errorMessage,
                        onRetry = { onEvent(TransactionsUiEvent.OnRetry) }
                    )
                }

                state.items.isEmpty() -> {
                    Text(
                        text = "No transactions yet",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = state.items,
                            key = { it.id }
                        ) { tx ->
                            TransactionRow(
                                tx = tx,
                                onClick = { onTransactionClick(tx.id) },
                                onDelete = { onEvent(TransactionsUiEvent.OnDeleteClick(tx)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TransactionRow(
    tx: Transaction,
    onClick: () -> Unit,
    onDelete: () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete(); true
            } else false
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = tx.category, style = MaterialTheme.typography.titleMedium)
                    if (!tx.note.isNullOrBlank()) {
                        Text(text = tx.note, style = MaterialTheme.typography.bodySmall)
                    }
                }
                Text(
                    text = tx.amount.toString(),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}


@Composable
private fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Error: $message")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}