package com.example.olineshop

import javax.inject.Inject

class FirebaseRepository @Inject constructor(private val firebaseSource: FirebaseSource) {
    fun signUpUser(email: String, password: String, fullName: String) = firebaseSource.signUpUser(email, password, fullName)

    fun signInUser(email: String, password: String) = firebaseSource.signInUser(email, password)


}