package com.example.olineshop

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.olineshop.ui.theme.NetworkControl
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SingUpViewModel @Inject constructor(
    private val repository: FirebaseRepository,
    private val firebaseAuth: FirebaseAuth ,
    private val networkControl: NetworkControl
): ViewModel() {


    private val _userStateFlow = MutableStateFlow<Resource<User>>(Resource.Loading(null))
    val userStateFlow = _userStateFlow
    fun signUpUser(email: String, password: String, fullName: String) : Flow<Resource<User>>{
       viewModelScope.launch {
         when{
             TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fullName) ->{
                 _userStateFlow.emit(Resource.Error("field must not be empty", null))
                 Log.d("TAG", "signUpUser: i'm in fill part")
             }
             password.length < 8 ->{
                 _userStateFlow.emit(Resource.Error("password must be more then 8", null))
                 Log.d("TAG", "signUpUser: i'm in password length")
             }
             networkControl.isConnected()->{
                 _userStateFlow.emit(Resource.Loading(null))
                 firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
                     if (task.result?.signInMethods?.size == 0) {
                         Log.d("TAG", "signUpUser: i'm check that no user with this email")
                         repository.signUpUser(email, password, fullName).addOnCompleteListener { signUpTask ->
                             if (signUpTask.isSuccessful) {
                                 firebaseAuth.currentUser?.sendEmailVerification()
                                 viewModelScope.launch{
                                     _userStateFlow.emit(Resource.Success(User(email = email, fullName = fullName)))
                                     Log.d("TAG", "signUpUser: su")
                                 }
                             } else {
                                 viewModelScope.launch {
                                     _userStateFlow.emit(Resource.Error(signUpTask.exception?.message.toString(),null))
                                 }
                             }
                         }
                     } else {
                         viewModelScope.launch {
                             _userStateFlow.emit(Resource.Error("email already exist" , null))
                         }
                     }
                 }
             }
             else ->{
                 _userStateFlow.emit(Resource.Error( "No internet connection" , null))
             }

         }
      }
     return userStateFlow
    }
}