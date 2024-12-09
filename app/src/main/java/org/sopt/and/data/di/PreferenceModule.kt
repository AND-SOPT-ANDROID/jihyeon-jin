package org.sopt.and.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.and.core.utils.PreferenceUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    @Singleton
    fun providePreferenceUtils(
        @ApplicationContext context: Context
    ): PreferenceUtil {
        val sharedPreferences = context.getSharedPreferences("wavve_prefs", Context.MODE_PRIVATE)
        return PreferenceUtil(sharedPreferences)
    }
}