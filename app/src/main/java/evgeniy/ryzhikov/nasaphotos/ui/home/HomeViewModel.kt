package evgeniy.ryzhikov.nasaphotos.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evgeniy.ryzhikov.nasaphotos.App
import evgeniy.ryzhikov.apod_module.api.ApodRepository
import evgeniy.ryzhikov.utils.models.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel : ViewModel() {

    @Inject lateinit var repository: ApodRepository

    private val _randomPhoto = MutableLiveData<ApiResult<Any>>()
    val randomPhoto: LiveData<ApiResult<Any>>
        get() = _randomPhoto

    init {
        App.appComponent.inject(this)
    }
    fun getRandomPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            _randomPhoto.postValue(repository.getRandomPhoto())
        }
    }

}