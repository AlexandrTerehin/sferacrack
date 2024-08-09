package presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import core.settings.SferaSetting
import core.settings.SferaUser
import domain.di.DomainDI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

//todo
internal class AuthorizationViewModel : ViewModel() {

    private val _flowIsAuthorization = MutableStateFlow<Boolean?>(null)
    val flowIsAuthorization = _flowIsAuthorization.asStateFlow()

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) {
            _flowIsAuthorization.emit(null)
        }
    }

    fun checkAuthorization(login: String, password: String, email: String) {
        SferaSetting.user = SferaUser(login, email, password)
        viewModelScope.launch(Dispatchers.IO) {
            val isAuthor = DomainDI.checkAuthorizationUseCase().invoke()
            _flowIsAuthorization.emit(isAuthor)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return AuthorizationViewModel() as T
            }
        }
    }
}