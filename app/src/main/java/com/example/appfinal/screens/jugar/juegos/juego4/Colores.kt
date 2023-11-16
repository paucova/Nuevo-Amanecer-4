package com.example.appfinal.screens.jugar.juegos.juego4

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.screens.home.noRippleClickable
import kotlin.random.Random

@Composable
fun Colores (navController: NavHostController) {
    // Fondo
    val azulClaro = Color(173, 216, 230)

    // Variables
    val images = remember { mutableStateOf(generateImages2()) }
    val deletedImages = remember { mutableStateOf(mutableListOf<DraggableImage2>()) }
    val deletedImageCount = remember { mutableStateOf(0) }
    val colorObjetivo = remember { mutableStateOf(generateRandomColor(images.value)) } // Color que se debe eliminar

    fun selectNewColorObjective() {
        if (images.value.isNotEmpty()) {
            colorObjetivo.value = generateRandomColor(images.value)
        } else {
            // No quedan círculos, fin del juego o reinicio
            // Puedes añadir la lógica necesaria aquí
        }
    }

    fun onCircleDeleted(image: DraggableImage2) {
        deletedImages.value.add(image)
        deletedImageCount.value++

        // Elimina el círculo correcto de la lista de imágenes
        images.value.remove(image)

        if (images.value.isNotEmpty()) {
            // Quedan círculos, selecciona un nuevo color objetivo
            selectNewColorObjective()
        } else {
            // No quedan círculos visibles, genera nuevos círculos
            val randomImageCount = Random.nextInt(1, 11)
            addNewImages2(images.value, randomImageCount)

            // Selecciona un nuevo color objetivo de entre los círculos restantes
            selectNewColorObjective()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = azulClaro)
    ) {
        // Botón de regreso a HomeScreen
        Button(
            onClick = {
                navController.navigate("Juego4Screen") {
                    popUpTo("Juego4Screen") {
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

        // Muestra el color objetivo en la parte superior
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(color = colorObjetivo.value, shape = CircleShape)
                .size(70.dp)
        )

        val visibleImages = images.value.filter { it.isVisible }
        if (visibleImages.isNotEmpty()) {
            visibleImages.forEach { image ->
                DraggableImage2(image = image, colorObjetivo = colorObjetivo.value) {
                    if (image.color == colorObjetivo.value) {
                        onCircleDeleted(image)
                    }
                }
            }
        } else {
            val randomImageCount = Random.nextInt(1, 11)
            addNewImages2(images.value, randomImageCount)

            // Selecciona un nuevo color objetivo de entre los círculos restantes
            selectNewColorObjective()
        }
    }
}

data class DraggableImage2(
    val id: Int,
    var offset: IntOffset,
    val color: Color,
    val radius: Int,
    var isVisible: Boolean = true
)

fun addNewImages2(images: MutableList<DraggableImage2>, imageCount: Int) {
    images.clear()

    for (id in 1..imageCount) {
        val xOffset = Random.nextInt(100, 1500)
        val yOffset = Random.nextInt(100, 1000)
        val color = generateRandomColor()
        val radius = Random.nextInt(150, 200)
        val isVisible = true

        images.add(
            DraggableImage2(
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
fun DraggableImage2(image: DraggableImage2, colorObjetivo: Color, onDeleteClick: () -> Unit) {
    Box(
        modifier = Modifier
            .offset { image.offset }
            .size(image.radius.dp)
            .fillMaxSize()
            .background(color = image.color, shape = CircleShape)
            .noRippleClickable {
                if (image.isVisible && image.color == colorObjetivo) {
                    onDeleteClick()
                }
            }
    ) {
        // Agrega el contenido de la imagen aquí, si es necesario
    }
}

fun generateImages2(): MutableList<DraggableImage2> {
    val images = mutableListOf<DraggableImage2>()

    for (id in 1..10) {
        val xOffset = Random.nextInt(100, 2000)
        val yOffset = Random.nextInt(100, 1000)
        val color = generateRandomColor()
        val radius = Random.nextInt(150, 200)

        images.add(
            DraggableImage2(
                id = id,
                offset = IntOffset(xOffset, yOffset),
                color = color,
                radius = radius
            )
        )
    }

    return images
}

fun generateRandomColor(): Color {
    return Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)
}

fun generateRandomColor(images: List<DraggableImage2>): Color {
    val availableColors = images.map { it.color }.distinct()
    return availableColors.random()
}