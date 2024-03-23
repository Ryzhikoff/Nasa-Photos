package evgeniy.ryzhikov.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.core.domain.SettingProvider
import evgeniy.ryzhikov.core.domain.SettingProviderImpl
import javax.inject.Singleton

@Module
class SettingProviderModule(private val applicationContext: Context) {

    @Singleton
    @Provides
    fun provideSettingProvider(): SettingProvider =
        SettingProviderImpl(applicationContext)
}