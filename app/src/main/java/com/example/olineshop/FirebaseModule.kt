package com.example.olineshop

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.olineshop.ui.theme.NetworkControl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun getFirebaseInstance() : FirebaseAuth = FirebaseAuth.getInstance()
    @Provides
    @Singleton
    fun getFirebaseSource(firebaseAuth: FirebaseAuth) : FirebaseSource = FirebaseSource(firebaseAuth)

    @Provides
    @Singleton
    fun getFirebaseRepository(firebaseSource: FirebaseSource) : FirebaseRepository = FirebaseRepository(firebaseSource)

    @Provides
    @Singleton
    fun getNetworkControl(@ApplicationContext context: Context) : NetworkControl = NetworkControl(context)

}
