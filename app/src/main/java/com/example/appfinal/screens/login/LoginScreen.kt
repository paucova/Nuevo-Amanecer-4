package com.example.appfinal.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.R

@Composable
fun LoginScreen (navController: NavHostController){
    val email = remember {
        mutableStateOf("123@tec.mx")
    }

    val password = remember {
        mutableStateOf("1234")
    }

    // Box para poner imagen de fondo
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Una columna para que se separe
        Column {
            // Primera parte: Mensajes
            Column(
                modifier = Modifier.offset(x = 250.dp, y = 100.dp)
            ) {
                Text(
                    text = "Inicia Sesión",
                    color = Color.White,
                    fontSize = 60.sp)

                Text(text = "Bienvenido",
                    color = Color.White,
                    fontSize = 30.sp)
            }

            // Segunda parte: Ingresar informacion
            Column(
                modifier = Modifier.offset(x = 250.dp, y = 150.dp)
            ) {
                Text(text = "Usuario",
                    color = Color.White,
                    fontSize = 15.sp)

                TextField(value = email.value, onValueChange = {
                    email.value = it
                }, placeholder = {
                    Text("Email")
                })

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Contraseña",
                    color = Color.White,
                    fontSize = 15.sp)

                TextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    placeholder = {
                        Text("Contraseña")
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )

            }

            // Tercera parte: Botones
            Column(
                modifier = Modifier.offset(x = 240.dp, y = 225.dp)
            ) {
                // Inicia sesión
                Box(
                    modifier = Modifier
                        .height(47.dp)
                        .width(300.dp)
                        .clickable {
                            navController.navigate("HomeScreen")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.iniciar_sesion),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth() // Asegura que la imagen llene el espacio del botón
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Agregar usuario
                Box(
                    modifier = Modifier
                        .height(47.dp)
                        .width(300.dp)
                        .clickable {
                            //navController.navigate("HomeScreen")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.agregar_usuario),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth() // Asegura que la imagen llene el espacio del botón
                    )
                }

            }


        }


    }
}