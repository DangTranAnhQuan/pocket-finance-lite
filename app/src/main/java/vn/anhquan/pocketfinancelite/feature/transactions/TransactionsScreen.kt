package vn.anhquan.pocketfinancelite.feature.transactions

import android.net.TetheringManager
import android.view.SurfaceControl
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TransactionsScreen(
    onAddClick: () -> Unit,
    onEditClick: (Long) -> Unit,
) {
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Transactions (placeholder)")

        Button(onClick = onAddClick) {
            Text("Add")
        }
    }
}