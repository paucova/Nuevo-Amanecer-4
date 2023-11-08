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
import kotlin.random.Random


@Composable
fun Juego3(navController: NavHostController) {
    val azulClaro = Color(173, 216, 230)
    var images by remember { mutableStateOf(generateImages3()) }
    var visibleImages by remember { mutableStateOf(images.filter { it.isVisible }) }
    var deletedImages by remember { mutableStateOf(mutableListOf<DraggableImage3>()) }
    var deletedImageCount by remember { mutableStateOf(0) }

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
                .align(Alignment.TopEnd) // Cambiado a Alignment.TopEnd
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

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Número de imágenes visibles: ${visibleImages.size}",
            modifier = Modifier
                .align(Alignment.TopStart) // Mantenido como Alignment.TopStart
                .padding(16.dp)
                .then(Modifier.fillMaxWidth()),
            fontSize = 20.sp
        )

        Text(
            text = "Número de imágenes eliminadas: $deletedImageCount",
            modifier = Modifier
                .align(Alignment.TopStart) // Mantenido como Alignment.TopStart
                .padding(top = 48.dp, start = 16.dp)
                .then(Modifier.fillMaxWidth()),
            fontSize = 20.sp
        )

        if (visibleImages.isNotEmpty()) {
            visibleImages.forEach { image ->
                DraggableImage3(image = image) {
                    deletedImages.add(image)
                    deletedImageCount++
                    visibleImages = visibleImages.filter { it != image }
                }
            }
        } else {
            val randomImageCount = Random.nextInt(1, 11) // Genera un número aleatorio entre 1 y 20
            addNewImages3(images, randomImageCount)
            visibleImages = images
        }
    }
}


data class DraggableImage3(
    val id: Int,
    var offset: IntOffset,
    val color: Color,
    val radius: Int,
    var isVisible: Boolean = true
)

fun addNewImages3(images: MutableList<DraggableImage3>, imageCount: Int) {
    images.clear()

    for (id in 1..imageCount) {
        val xOffset = Random.nextInt(100, 1500)
        val yOffset = Random.nextInt(100, 1000)
        val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
        val radius = Random.nextInt(150, 200) // Radio aleatorio para los círculos
        val isVisible = true

        images.add(
            DraggableImage3(
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
fun DraggableImage3(image: DraggableImage3, onDeleteClick: () -> Unit) {
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
        Text(
            text = image.id.toString(), // Muestra el número de imagen en el círculo
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

fun generateImages3(): MutableList<DraggableImage3> {
    val images = mutableListOf<DraggableImage3>()

    for (id in 1..10) {
        val xOffset = Random.nextInt(100, 2000)
        val yOffset = Random.nextInt(100, 1000)
        val color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
        val radius = Random.nextInt(150, 200)

        images.add(
            DraggableImage3(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                color = color,
                radius = radius
            )
        )
    }

    return images
}