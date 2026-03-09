package vn.anhquan.pocketfinancelite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Import Interface từ tầng Domain
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
// CHÚ Ý IMPORT THÊM class Impl từ tầng Data
import vn.anhquan.pocketfinancelite.data.repository.TransactionRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(
        impl: TransactionRepositoryImpl
    ): TransactionRepository

}