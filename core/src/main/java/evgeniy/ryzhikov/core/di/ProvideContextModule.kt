package evgeniy.ryzhikov.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProvideContextModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context = context
}