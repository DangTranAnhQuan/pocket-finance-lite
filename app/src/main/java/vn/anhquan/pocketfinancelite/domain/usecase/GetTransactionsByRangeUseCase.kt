package vn.anhquan.pocketfinancelite.domain.usecase

import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionsByRangeUseCase @Inject constructor(
    private val repo: TransactionRepository
){
    operator fun invoke(from: Long, to: Long) = repo.observeByRange(from, to)
}