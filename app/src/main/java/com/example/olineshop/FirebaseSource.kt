package com.example.olineshop

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class FirebaseSource @Inject constructor(private val firebaseAuth: FirebaseAuth){

    fun signUpUser(email:String,password:String,fullName:String) = firebaseAuth.createUserWithEmailAndPassword(email,password)

    fun signInUser(email: String,password: String) = firebaseAuth.signInWithEmailAndPassword(email,password)
}