package com.example.olineshop


import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createAccount(
    singUpViewModel: SingUpViewModel
) {
    val context: Context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val imeState = rememberImeState()
        val scrollState = rememberScrollState()

        LaunchedEffect(key1 = imeState.value) {
            if (imeState.value) {
                scrollState.scrollTo(scrollState.maxValue)
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.size(80.dp))
            Card(
                modifier = Modifier.size(100.dp)
            ) {

            }
            Spacer(modifier = Modifier.size(80.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // username
                val email = remember {
                    EditTextViewModel()
                }
                // crate a edit text for a email
                OutlinedTextField(
                    value = email.changeText.value,
                    onValueChange = {
                        email.changeText.value = it
                    },
                    placeholder = {
                        Text(text = "Email")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))

                val visibility = remember {
                    mutableStateOf(false)
                }
                val visible = if (!visibility.value) { // icon for  appear  the password according to variable visibility
                    remember {
                        mutableStateOf(Icons.Rounded.VisibilityOff)
                    }
                } else {
                    remember {
                        mutableStateOf(Icons.Rounded.Visibility)
                    }
                }
                val showPassword = if (!visibility.value) { //for show the password according to variable visible
                    remember {
                        mutableStateOf(PasswordVisualTransformation())
                    }
                } else {
                    remember {
                        mutableStateOf(VisualTransformation.None)
                    }
                }
                //create a object for password for EditTextViewModel
                val password = remember {
                    EditTextViewModel()
                }
                // crate a edit text for a password
                OutlinedTextField(
                    value = password.changeText.value,
                    onValueChange = {
                        password.changeText.value = it
                    },
                    placeholder = {

                        Text(text = "Password")

                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        // icon for visibility of password
                        IconButton(
                            onClick = { visibility.value = !visibility.value },
                        ) {
                            Icon(
                                imageVector = visible.value,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    },
                    visualTransformation = showPassword.value
                )
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .padding(start = 20.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))


                //create a object for password for EditTextViewModel
                val passwordC = remember {
                    EditTextViewModel()
                }
                // crate a edit text for a password
                OutlinedTextField(
                    value = passwordC.changeText.value,
                    onValueChange = {
                        passwordC.changeText.value = it
                    },
                    placeholder = {

                        Text(text = "Password")

                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        // icon for visibility of password
                        IconButton(
                            onClick = { visibility.value = !visibility.value },
                        ) {
                            Icon(
                                imageVector = visible.value,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                    },
                    visualTransformation = showPassword.value
                )
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.size(40.dp))
                GradientButton(
                    text = "Sing Up" ,
                    textColor = Color.Black ,
                    gradient = Brush.horizontalGradient(
                        colorStops = arrayOf(
                            Pair(0.2f , Color(0xFF6C14BC)),
                            Pair(0.7f, Color(0xFFE53DD1))
                        )
                    )
                ){
                   singUpViewModel.signUpUser(email.changeText.value , password.changeText.value , "")
                }

//                Row(
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Spacer(modifier = Modifier.weight(1f))
//
//                    IconRegister(icon = Icons.Rounded.Email, description ="Email" , tint = Red ){
//
//                    }
//                    Spacer(modifier = Modifier.weight(1f))
//                    IconRegister(icon = Icons.Rounded.Facebook, description ="FaceBook" , tint = Blue){}
//                    Spacer(modifier = Modifier.weight(1f))
//                }
//
//            }


            }
        }
    }
}

@Composable
fun IconRegister(
    icon : ImageVector,
    description  : String ,
    tint : Color,
    onClick : ()-> Unit
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            imageVector = icon  ,
            contentDescription = description ,
            tint = tint ,
            modifier = Modifier.size(200.dp)
        )
    }
}

