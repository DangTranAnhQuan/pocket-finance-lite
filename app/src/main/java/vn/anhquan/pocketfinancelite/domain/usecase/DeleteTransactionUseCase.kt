package vn.anhquan.pocketfinancelite.domain.usecase

import vn.anhquan.pocketfinancelite.domain.model.Transaction
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val repo: TransactionRepository
) {
    suspend operator fun invoke(tx: Transaction) = repo.delete(tx)
}
