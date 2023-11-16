package com.example.appfinal.screens.tarjetas.nuevaTarjeta

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appfinal.R
import com.example.appfinal.staticElements.LoadCategories
import com.example.appfinal.staticElements.LoadImages
import com.example.appfinal.viewModel.TarjetasViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AgregarTarjetaScreen(navController: NavHostController) {
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
                ImagePicker()
            }
        }


        }
    }



}
private fun generateTimestamp(): String {
    val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    return timestamp
}

@Composable
fun ImagePicker() {
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val tarjetasViewModel: TarjetasViewModel = viewModel()
    var categoryChosen by remember{ mutableStateOf("") }
    var textChosen by remember{ mutableStateOf("") }
    var dataSourceGiven by remember { mutableStateOf(-1) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri.value = uri
            uri?.let {
                val inputStream = context.contentResolver.openInputStream(it)
                val imageName = "appImage_${generateTimestamp()}"
                val outputStream =
                    context.openFileOutput(imageName, Context.MODE_PRIVATE)

                // Copy the selected image to internal storage
                inputStream?.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                        tarjetasViewModel.saveImageReference(imageName, categoryChosen, textChosen, dataSourceGiven)
                    }

                }
            }
        }

    Column {
        OutlinedTextField(value = categoryChosen,
            onValueChange = {categoryChosen = it},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text))

        OutlinedTextField(value = textChosen,
            onValueChange = {textChosen = it},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text))


        Button(onClick = {
            launcher.launch("image/*")
        }
        ) {
            Text("Select Image")
        }

        Button(onClick = {
            //LoadCategories()
            //LoadImages()
        }
        ) {
            Text("Select Image")
        }

    }}
