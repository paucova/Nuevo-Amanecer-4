package com.example.appfinal.screens.jugar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.R

@Composable
fun JugarScreen (navController: NavHostController) {
    // Establecer el fondo azul claro
    val azulClaro = Color(173, 216, 230)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a HomeScreen
        Button(
            onClick = {
                navController.navigate("HomeScreen") {
                    popUpTo("HomeScreen") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = "Regresar",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        // Para que las dos rows no se overlap
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Row 1: burbuja 1 y 2
            Row() {
                // Burbuja 1
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .clickable {
                                navController.navigate("Juego1")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_1),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Burbuja 2
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .clickable {
                                navController.navigate("Juego2")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_2),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // Row 2: burbuja 3 y 4
            Row() {
                // Burbuja 3
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .clickable {
                                navController.navigate("Juego3")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_3),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Burbuja 4
                Column() {
                    Box(
                        modifier = Modifier
                            .size(400.dp) // Ajusta el tamaño del botón según sea necesario
                            .clickable {
                                navController.navigate("Juego4Screen")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_4),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

    }

}