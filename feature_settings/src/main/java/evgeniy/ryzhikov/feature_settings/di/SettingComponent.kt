package evgeniy.ryzhikov.feature_settings.di

import dagger.Component
import evgeniy.ryzhikov.core.di.SettingProviderModule
import evgeniy.ryzhikov.feature_settings.ui.SettingsFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SettingProviderModule::class
    ]
)
interface SettingComponent {

    fun inject(settingsFragment: SettingsFragment)
}