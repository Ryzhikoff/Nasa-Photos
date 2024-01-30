package evgeniy.ryzhikov.nasaphotos

import android.app.Application
import evgeniy.ryzhikov.apod_module.di.DaggerApodComponent
import evgeniy.ryzhikov.database_module.di.DaggerDatabaseComponent
import evgeniy.ryzhikov.database_module.di.DatabaseModule
import evgeniy.ryzhikov.nasaphotos.di.AppComponent
import evgeniy.ryzhikov.nasaphotos.di.DaggerAppComponent
import evgeniy.ryzhikov.nasaphotos.di.modules.DomainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val apodProvider = DaggerApodComponent.create()
        val databaseProvider = DaggerDatabaseComponent.builder().databaseModule(DatabaseModule(this)).build()
        appComponent = DaggerAppComponent.builder()
            .apodProvider(apodProvider)
            .databaseProvider(databaseProvider)
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}