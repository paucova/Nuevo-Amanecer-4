package com.example.appfinal.screens.tarjetas.nuevaCategoria


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun AgregarCategoriaScreen(navController: NavHostController) {
    val azulClaro = Color(173, 216, 230)
    Column {
        Row {
            Box(
                modifier = Modifier
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
            }
            Row {
                Box(modifier = Modifier
                    .background(color = azulClaro)){
                    CategoryPicker()
                }
            }
        }
    }



}

@Composable
fun CategoryPicker() {
    val tarjetasViewModel: TarjetasViewModel = viewModel()
    var category by remember{ mutableStateOf("") }


    Column {
        OutlinedTextField(value = category,
            onValueChange = {category = it},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text))

        Button(onClick = {
            tarjetasViewModel.nuevaCategoria(category)
        }
        ) {
            Text("Add Category")
        }


    }
}
