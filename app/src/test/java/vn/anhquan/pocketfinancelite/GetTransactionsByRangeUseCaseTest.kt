package vn.anhquan.pocketfinancelite

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import vn.anhquan.pocketfinancelite.domain.model.Transaction
import vn.anhquan.pocketfinancelite.domain.model.TransactionType
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import vn.anhquan.pocketfinancelite.domain.usecase.GetTransactionsByRangeUseCase

class GetTransactionsByRangeUseCaseTest {

    private val repository: TransactionRepository = mockk()
    private val useCase = GetTransactionsByRangeUseCase(repository)

    private val fakeTransactions = listOf(
        Transaction(
            id = 1L,
            amount = 50000.0,
            type = TransactionType.EXPENSE,
            category = "FOOD",
            note = "Lunch",
            occurredAtEpochMillis = 1_000_000L
        ),
        Transaction(
            id = 2L,
            amount = 100000.0,
            type = TransactionType.INCOME,
            category = "SALARY",
            note = null,
            occurredAtEpochMillis = 2_000_000L
        )
    )

    // Test 1: trả về đúng danh sách từ repository
    @Test
    fun `invoke returns transactions from repository`() = runTest {
        every { repository.observeByRange(any(), any()) } returns flowOf(fakeTransactions)

        val result = useCase(0L, Long.MAX_VALUE).first()

        assertEquals(2, result.size)
        assertEquals("FOOD", result[0].category)
    }

    // Test 2: gọi đúng tham số from/to
    @Test
    fun `invoke calls repository with correct range`() = runTest {
        val from = 1_000L
        val to = 9_000L
        every { repository.observeByRange(from, to) } returns flowOf(emptyList())

        useCase(from, to).first()

        verify { repository.observeByRange(from, to) }
    }

    // Test 3: trả về empty list khi không có data
    @Test
    fun `invoke returns empty list when no transactions`() = runTest {
        every { repository.observeByRange(any(), any()) } returns flowOf(emptyList())

        val result = useCase(0L, Long.MAX_VALUE).first()

        assertEquals(0, result.size)
    }
}