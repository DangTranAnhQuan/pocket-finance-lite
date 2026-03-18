package vn.anhquan.pocketfinancelite.domain.usecase

import kotlinx.coroutines.flow.Flow
import vn.anhquan.pocketfinancelite.domain.model.MonthlySummary
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import javax.inject.Inject

class GetMonthlySummaryUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {
    operator fun invoke(from: Long, to: Long): Flow<List<MonthlySummary>> =
        repository.observeMonthlySummary(from, to)
}
