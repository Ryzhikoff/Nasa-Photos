package evgeniy.ryzhikov.nasaphotos.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class DomainModule(private val context: Context) {
    @Provides
    fun provideContext() = context
}