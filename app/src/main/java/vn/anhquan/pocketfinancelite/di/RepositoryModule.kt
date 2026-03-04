package vn.anhquan.pocketfinancelite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import vn.anhquan.pocketfinancelite.domain.repository.TransactionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds @Singleton
    abstract fun bindTransactionRepository(impl: TransactionRepository): TransactionRepository
}