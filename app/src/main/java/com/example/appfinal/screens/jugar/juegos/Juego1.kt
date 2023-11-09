package com.example.appfinal.screens.jugar.juegos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import kotlin.random.Random
import androidx.compose.ui.unit.sp
import com.example.appfinal.screens.jugar.juegos.juego4.DraggableImage3
import com.example.appfinal.screens.jugar.juegos.juego4.generateImages3

@Composable
fun Juego1(navController: NavHostController) {
    val azulClaro = Color(173, 216, 230)

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
                fontWeight = FontWeight.Bold
            )
        }

        Column (
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Text(
                text = "Número de imágenes visibles: ${visibleImages.size}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Número de imágenes eliminadas: $deletedImageCount",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (visibleImages.isNotEmpty()) {
                visibleImages.forEach { image ->
                    DraggableImage(image = image) {
                        deletedImages.add(image)
                        deletedImageCount++
                        visibleImages = visibleImages.filter { it != image }
                    }
                }
            } else {
                val randomImageCount = Random.nextInt(1, 11) // Genera un número aleatorio entre 1 y 20
                addNewImages(images, randomImageCount)
                visibleImages = images
            }
        }
    }
}

data class DraggableImage(
    val id: Int,
    var offset: IntOffset,
    val color: Color,
    val radius: Int,
    var isVisible: Boolean = true
)

fun addNewImages(images: MutableList<DraggableImage>, imageCount: Int) {
    images.clear()

    for (id in 1..imageCount) {
        val xOffset = Random.nextInt(100, 1500)
        val yOffset = Random.nextInt(100, 1000)
        val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
        val radius = Random.nextInt(150, 200) // Radio aleatorio para los círculos
        val isVisible = true

        images.add(
            DraggableImage(
                id = images.size + 1,
                offset = IntOffset(xOffset, yOffset),
                color = color,
                radius = radius,
                isVisible = isVisible
            )
        )
    }
}

@Composable
fun DraggableImage(image: DraggableImage, onDeleteClick: () -> Unit) {
    Box(
        modifier = Modifier
            .offset { image.offset }
            .size(image.radius.dp)
            .fillMaxSize()
            .background(color = image.color, shape = CircleShape)
            .clickable {
                image.isVisible = !image.isVisible
                onDeleteClick()
            }
    ) {
        // Agrega el contenido de la imagen aquí, si es necesario
    }
}

fun generateImages(): MutableList<DraggableImage> {
    val images = mutableListOf<DraggableImage>()

    for (id in 1..10) {
        val xOffset = Random.nextInt(100, 2000)
        val yOffset = Random.nextInt(100, 1000)
        val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
        val radius = Random.nextInt(150, 200)

        images.add(
            DraggableImage(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                color = color,
                radius = radius
            )
        )
    }

    return images
}