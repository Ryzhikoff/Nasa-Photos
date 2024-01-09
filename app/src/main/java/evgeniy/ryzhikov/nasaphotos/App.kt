package evgeniy.ryzhikov.nasaphotos

import android.app.Application
import evgeniy.ryzhikov.apod_module.di.DaggerApodComponent
import evgeniy.ryzhikov.nasaphotos.di.AppComponent
import evgeniy.ryzhikov.nasaphotos.di.DaggerAppComponent
import evgeniy.ryzhikov.nasaphotos.di.modules.DomainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val apodProvider = DaggerApodComponent.create()
        appComponent = DaggerAppComponent.builder()
                .apodProvider(apodProvider)
                .domainModule(DomainModule(this))
                .build()
        }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}