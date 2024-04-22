package app.gratum.kawaishiryuappnew.core

import app.gratum.kawaishiryuappnew.data.model.UserModel


sealed class ViewModelState {

    //UserState
    data class UserRegisterSuccesfully(val user: UserModel) : ViewModelState()
    data class SignInUserSuccesfully(val user: UserModel) : ViewModelState()
    data class UserRegisterDbSyccesfully(val userModel: UserModel) : ViewModelState()
    data class UserModifiedSuccesfully(val userModel: UserModel) : ViewModelState()
    //data class UserDataStoreSuccesfully(val userDataModel: UserModelDataStore) : ViewModelState()

//    //TecState
//    data class TecModifiedSuccesfully(val model: MoviemientosModel): ViewModelState()

    data class Error(val message: String) : ViewModelState() //Register Or Update
    data class Logged(val boolean: Boolean) : ViewModelState()
    data class SignOutSucces(val boolean: Boolean) : ViewModelState()
    data class Succes(val message: String) : ViewModelState()

    object Empty : ViewModelState() //Register or Update
    object Loading : ViewModelState() //Register or Update
    object None : ViewModelState()

    data class SignOutSuccess(val message: String = "Cierre de sesión exitoso") : ViewModelState()
    data class SignOutError(val errorMessage: String) : ViewModelState()

    //RegisterDojo
    data class Loading2(val message: String = "Cargando...") : ViewModelState()
    data class Success2(val message: String = "Succes...") : ViewModelState()
    data class Error2(val message: String = "Error...") : ViewModelState()
    data class Nothing2(val message: String = "Nada...") : ViewModelState()

//    data class GetListSuccessfullyDojo(val model: MutableList<DojosModel>) : ViewModelState()
//
//    //User
//    data class UserLoaded(val user: UserModel) : ViewModelState()

}