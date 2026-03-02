package vn.anhquan.pocketfinancelite.feature.transaction_form

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
fun TransactionFormScreen(
   onBack: () -> Unit,
) {
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text("Transactions Form(placeholder)")

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}