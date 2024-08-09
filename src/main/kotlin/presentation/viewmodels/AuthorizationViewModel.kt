package presentation.viewmodels

import androidx.lifecycle.ViewModel
import domain.usecase.CheckAuthorizationUseCase

//todo
internal class AuthorizationViewModel(
    private val checkAuthorizationUseCase: CheckAuthorizationUseCase
) : ViewModel() {


}