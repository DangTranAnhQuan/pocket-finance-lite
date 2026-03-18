import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import vn.anhquan.pocketfinancelite.domain.model.MonthlySummary
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import vn.anhquan.pocketfinancelite.domain.usecase.GetMonthlySummaryUseCase

class GetMonthlySummaryUseCaseTest {

    private val repository: TransactionRepository = mockk()
    private val useCase = GetMonthlySummaryUseCase(repository)

    @Test
    fun `balance is income minus expense`() {
        val summary = MonthlySummary(
            year = 2026, month = 3,
            totalIncome = 5_000_000.0,
            totalExpense = 3_200_000.0,
        )
        assertEquals(1_800_000.0, summary.balance, 0.01)
    }

    @Test
    fun `invoke returns summary from repository`() = runTest {
        val fakeSummary = listOf(
            MonthlySummary(2026, 3, 5_000_000.0, 3_200_000.0)
        )
        every { repository.observeMonthlySummary(any(), any()) } returns flowOf(fakeSummary)

        val result = useCase(0L, Long.MAX_VALUE).first()

        assertEquals(1, result.size)
        assertEquals(1_800_000.0, result[0].balance, 0.01)
    }
}
