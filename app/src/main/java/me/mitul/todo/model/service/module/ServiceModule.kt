package me.mitul.todo.model.service.module

import me.mitul.todo.model.service.impl.AccountServiceImpl
import me.mitul.todo.model.service.impl.ConfigurationServiceImpl
import me.mitul.todo.model.service.impl.LogServiceImpl
import me.mitul.todo.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.mitul.todo.model.service.AccountService
import me.mitul.todo.model.service.ConfigurationService
import me.mitul.todo.model.service.LogService
import me.mitul.todo.model.service.StorageService

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideLogService(impl: LogServiceImpl): LogService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

    @Binds
    abstract fun provideConfigurationService(impl: ConfigurationServiceImpl): ConfigurationService
}
