package vn.anhquan.pocketfinancelite.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import vn.anhquan.pocketfinancelite.feature.transaction_form.TransactionFormScreen
import vn.anhquan.pocketfinancelite.feature.transactions.TransactionsScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.TRANSACTIONS,
        modifier = modifier
    ) {
        composable(route = Routes.TRANSACTIONS) {
            TransactionsScreen(
                onAddClick = { navController.navigate(Routes.TRANSACTION_FORM) },
                onEditClick = { id ->
                    navController.navigate("${Routes.TRANSACTION_FORM}?${Routes.ARG_TRANSACTION_ID}=$id")
                }
            )
        }

        composable(route = "${Routes.TRANSACTION_FORM}?${Routes.ARG_TRANSACTION_ID}={${Routes.ARG_TRANSACTION_ID}}") {
            TransactionFormScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
