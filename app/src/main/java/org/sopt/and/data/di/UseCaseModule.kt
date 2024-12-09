package org.sopt.and.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.domain.repository.GetMyHobbyRepository
import org.sopt.and.domain.repository.UserLoginRepository
import org.sopt.and.domain.repository.UserRegisterRepository
import org.sopt.and.domain.usecase.GetMyHobbyUseCase
import org.sopt.and.domain.usecase.LoginUseCase
import org.sopt.and.domain.usecase.RegisterUserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideRegisterUserUseCase(userRepository: UserRegisterRepository): RegisterUserUseCase {
        return RegisterUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUserUseCase(userRepository: UserLoginRepository): LoginUseCase {
        return LoginUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideGetMyHobbyUseCase(userRepository: GetMyHobbyRepository): GetMyHobbyUseCase {
        return GetMyHobbyUseCase(userRepository)
    }
}