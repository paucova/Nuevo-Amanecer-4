package com.example.appfinal.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.R

@Composable
fun HomeScreen(navController: NavHostController) {
    // Box para poner imagen de fondo
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo de imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_oceano),
            contentDescription = null, // Puedes proporcionar una descripción si es necesario
            modifier = Modifier.fillMaxSize(),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )

        // Tres botones principales | todos centrados
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Dividir aprender y juegos
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                // Aprender
                Column () {
                    Box(
                        modifier = Modifier
                            .size(550.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                navController.navigate("AprenderScreen")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_aprender),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize() // Asegura que la imagen llene el espacio del botón
                        )
                    }
                }

                // Juegos
                Column () {
                    Box(
                        modifier = Modifier
                            .size(550.dp) // Ajusta el tamaño del botón según sea necesario
                            .noRippleClickable {
                                navController.navigate("JugarScreen")
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.burbuja_jugar),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize() // Asegura que la imagen llene el espacio del botón
                        )
                    }
                }
            }

            // Tarjetas
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .size(300.dp) // Ajusta el tamaño del botón según sea necesario
                        .noRippleClickable {
                            navController.navigate("TarjetasScreen")
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tarjetas),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize() // Asegura que la imagen llene el espacio del botón
                    )
                }
            }

        }

        // Esquina superior derecha de cerrar sesión
        Column(
            modifier = Modifier.offset(x = 1000.dp, y = 15.dp)
        ) {
            // Inicia sesión
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(400.dp)
                    .clickable {
                        navController.navigate("LoginScreen")
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cerrar_sesion),
                    contentDescription = null,
                    //modifier = Modifier.fillMaxSize()
                )
            }
        }

    }
}

// para quitar el efecto del click
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}