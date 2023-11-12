package com.example.appfinal.screens.aprender.categorias

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun ViewCategoriesScreen(
    navController: NavHostController,
    tarjetasViewModel: TarjetasViewModel,
    onItemClick: (String) ->Unit = {}
) {
    val azulTarjeta = Color(210, 239, 255)
    // lazy column para desplegar las tarjetas de las categorías
    LazyColumn {
        // los items son aquellos guardados en la base de datos (categorías)
        items(tarjetasViewModel.categories.value) { categoria ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    // agregamos este modifier para ir a la sección de las columnas
                    .clickable {
                        onItemClick(categoria.text)
                        navController.navigate("ColumnasScreen/${categoria.text}")
                    },
                shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // se despliega el texto de la categoría
                    Text(
                        text = "${categoria.text}",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}