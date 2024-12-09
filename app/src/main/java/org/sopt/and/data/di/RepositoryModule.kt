package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.api.UserService
import org.sopt.and.data.repository.GetMyHobbyRepositoryImpl
import org.sopt.and.data.repository.UserLoginRepositoryImpl
import org.sopt.and.data.repository.UserRegisterRepositoryImpl
import org.sopt.and.domain.repository.GetMyHobbyRepository
import org.sopt.and.domain.repository.UserLoginRepository
import org.sopt.and.domain.repository.UserRegisterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRegisterRepository(userService: UserService): UserRegisterRepository {
        return UserRegisterRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideUserLoginRepository(userService: UserService): UserLoginRepository {
        return UserLoginRepositoryImpl(userService)
    }

    @Provides
    @Singleton
    fun provideGetMyHobbyRepository(userService: UserService): GetMyHobbyRepository {
        return GetMyHobbyRepositoryImpl(userService)
    }
}