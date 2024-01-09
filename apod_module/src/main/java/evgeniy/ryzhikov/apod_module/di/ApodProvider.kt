package evgeniy.ryzhikov.apod_module.di

import evgeniy.ryzhikov.apod_module.api.ApodApi

interface ApodProvider {
    fun provideApod(): ApodApi
}