package com.example.appfinal.screens.jugar.juegos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appfinal.screens.jugar.juegos.juego4.DraggableImage3
import com.example.appfinal.screens.jugar.juegos.juego4.addNewImages3
import com.example.appfinal.screens.jugar.juegos.juego4.generateImages3
import kotlin.random.Random

@Composable
fun Juego3(navController: NavHostController) {
    val azulClaro = Color(173, 216, 230)
    var images by remember { mutableStateOf(generateImages3()) }
    var visibleImages by remember { mutableStateOf(listOf<DraggableImage3>()) }
    var deletedImages by remember { mutableStateOf(mutableListOf<DraggableImage3>()) }
    var deletedImageCount by remember { mutableStateOf(0) }
    var currentNumber by remember { mutableStateOf(1) }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a HomeScreen
        Button(
            onClick = {
                navController.navigate("JugarScreen") {
                    popUpTo("JugarScreen") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
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
                fontWeight = FontWeight.Bold)
        }
    }
}
