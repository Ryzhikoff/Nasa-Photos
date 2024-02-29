package evgeniy.ryzhikov.search_module.di.modules

import evgeniy.ryzhikov.search_module.di.SearchComponent

interface SearchComponentProvider {
    fun getSearchComponent(): SearchComponent
}