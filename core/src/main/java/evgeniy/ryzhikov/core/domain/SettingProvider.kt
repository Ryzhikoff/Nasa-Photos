package evgeniy.ryzhikov.core.domain

interface SettingProvider {

    var pageSize: Float
    var initialLoadSize: Float
    var bufferSize: Float

}