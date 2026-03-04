import vn.anhquan.pocketfinancelite.domain.model.Transaction
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import javax.inject.Inject

class AddOrUpdateTransactionUseCase @Inject constructor(
    private val repo: TransactionRepository
) {
    suspend operator fun invoke(tx: Transaction): Long = repo.upsert(tx)
}
