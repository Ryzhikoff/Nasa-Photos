package evgeniy.ryzhikov.remote.di.modules

import dagger.Module
import dagger.Provides
import evgeniy.ryzhikov.remote.data.images.repository.SearchRepository
import evgeniy.ryzhikov.remote.domain.SearchUseCase
import javax.inject.Singleton

@Module(
    includes = [
        SearchModule::class
    ]
)
class SearchUseCaseModule {

    @Singleton
    @Provides
    fun provideSearchUseCase(searchRepository: SearchRepository): SearchUseCase =
        SearchUseCase(searchRepository)
}