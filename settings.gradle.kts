pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Nasa Photos"
include(":app")
include(":utils")
include(":database")
include(":remote")
include(":feature_random_photo")
include(":feature_search")
include(":feature_favorites")
include(":feature_details")
include(":core")
include(":setting_provider")
include(":feature_settings")
