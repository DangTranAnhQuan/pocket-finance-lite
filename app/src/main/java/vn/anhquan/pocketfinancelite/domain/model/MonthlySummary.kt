package vn.anhquan.pocketfinancelite.domain.model

data class MonthlySummary(
    val year: Int,
    val month: Int,
    val totalIncome: Double,
    val totalExpense: Double,
) {
    val balance: Double get() = totalIncome - totalExpense
}
