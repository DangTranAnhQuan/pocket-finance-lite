package vn.anhquan.pocketfinancelite.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import vn.anhquan.pocketfinancelite.feature.transaction_form.TransactionFormScreen
import vn.anhquan.pocketfinancelite.feature.transactions.TransactionsScreenRoot

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
            TransactionsScreenRoot(
                onAddClick = { navController.navigate(Routes.TRANSACTION_FORM) },
                onEditClick = { id ->
                    navController.navigate("${Routes.TRANSACTION_FORM}?${Routes.ARG_TRANSACTION_ID}=$id")
                }
            )
        }

        composable(
            route = "${Routes.TRANSACTION_FORM}?${Routes.ARG_TRANSACTION_ID}={${Routes.ARG_TRANSACTION_ID}}",
            arguments = listOf(
                navArgument(Routes.ARG_TRANSACTION_ID) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getLong(Routes.ARG_TRANSACTION_ID) ?: -1L

            TransactionFormScreen(
                transactionId = if (id == -1L) null else id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}