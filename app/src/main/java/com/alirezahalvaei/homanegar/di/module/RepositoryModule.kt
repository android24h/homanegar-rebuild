package com.alirezahalvaei.homanegar.di.module

import com.alirezahalvaei.homanegar.data.settings.SettingsRepositoryImpl
import com.alirezahalvaei.homanegar.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(impl: SettingsRepositoryImpl): SettingsRepository
}