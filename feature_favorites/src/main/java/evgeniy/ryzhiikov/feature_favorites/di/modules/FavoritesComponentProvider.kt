package evgeniy.ryzhiikov.feature_favorites.di.modules

import evgeniy.ryzhiikov.feature_favorites.di.FavoritesComponent

interface FavoritesComponentProvider {
    fun getFavoritesComponent(): FavoritesComponent
}