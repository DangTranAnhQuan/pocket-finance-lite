package vn.anhquan.pocketfinancelite.data.local.dto

data class MonthlySummaryDto(
    val year: Int,
    val month: Int,
    val totalIncome: Double,
    val totalExpense: Double,
)
