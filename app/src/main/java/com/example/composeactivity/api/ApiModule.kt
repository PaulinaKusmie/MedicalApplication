package com.example.composeactivity.api

import com.example.composeactivity.api.impl.UserAPIImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {
    @Binds
    abstract fun bindUserAPI(
        impl: UserAPIImpl
    ): UserAPI
}
