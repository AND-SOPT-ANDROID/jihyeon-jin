package org.sopt.and.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.and.BuildConfig.BASE_URL
import org.sopt.and.data.api.UserService
import org.sopt.and.data.repository.GetMyHobbyRepositoryImpl
import org.sopt.and.data.repository.UserLoginRepositoryImpl
import org.sopt.and.data.repository.UserRegisterRepositoryImpl
import org.sopt.and.domain.repository.GetMyHobbyRepository
import org.sopt.and.domain.repository.UserLoginRepository
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.domain.repository.UserRegisterRepository
import org.sopt.and.domain.usecase.GetMyHobbyUseCase
import org.sopt.and.domain.usecase.LoginUseCase
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // 실제 서버 URL
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }


    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

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
