package com.example.appfinal.screens.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appfinal.screens.aprender.AprenderScreen
import com.example.appfinal.screens.aprender.categorias.ViewCategoriesScreen
import com.example.appfinal.screens.aprender.columnas.ColumnasScreen
import com.example.appfinal.screens.aprender.viewImages.ViewImagesScreen
import com.example.appfinal.screens.home.HomeScreen
import com.example.appfinal.screens.jugar.JugarScreen
import com.example.appfinal.screens.jugar.juegos.Juego1
import com.example.appfinal.screens.jugar.juegos.Juego2
import com.example.appfinal.screens.jugar.juegos.Juego3
import com.example.appfinal.screens.jugar.juegos.juego4.Colores
import com.example.appfinal.screens.jugar.juegos.juego4.Juego4Screen
import com.example.appfinal.screens.jugar.juegos.juego4.Numeros
import com.example.appfinal.screens.jugar.juegos.juego4.Resta
import com.example.appfinal.screens.jugar.juegos.juego4.Suma
import com.example.appfinal.screens.login.AgregarUsuario
import com.example.appfinal.screens.login.LoginScreen
import com.example.appfinal.screens.tarjetas.TarjetasScreen
import com.example.appfinal.viewModel.TarjetasViewModel

@Composable
fun NavigationScreen(tarjetasViewModel: TarjetasViewModel, context: Context){

    // #1  Crear el objeto de NavController
    val navController = rememberNavController()

    // #2 Crear el contenedor y definir las rutas
    NavHost(navController = navController, startDestination = "LoginScreen"){
        // Lo del login
        composable("LoginScreen"){
            LoginScreen(navController)
        }

        // Agregar a un usuario
        composable("AgregarUsuario"){
            AgregarUsuario(navController)
        }

        // Paginas dentro de HomeScreen
        composable("HomeScreen") {
            HomeScreen(navController)
        }

        composable("AprenderScreen") {
            AprenderScreen(navController, tarjetasViewModel)
        }

        composable("ViewCategoriesScreen"){
            ViewCategoriesScreen(navController, tarjetasViewModel)
        }

        composable("ColumnasScreen/{text}"){
            val categoria = it.arguments?.getString("text")
            ColumnasScreen(navController, categoria)
        }

        composable("ViewImagesScreen/{numero}/{text}"){
            val categoria = it.arguments?.getString("text")
            val numero = it.arguments?.getString("numero")
            ViewImagesScreen(navController, tarjetasViewModel, context, categoria, numero)
        }

        composable("JugarScreen") {
            JugarScreen(navController)
        }

        composable("TarjetasScreen") {
            TarjetasScreen(navController)
        }

        // Videojuegos
        composable("Juego1") {
            Juego1(navController, tarjetasViewModel, context)
        }

        composable("Juego2") {
            Juego2(navController)
        }

        composable("Juego3") {
            Juego3(navController)
        }

        composable("Juego4Screen") {
            Juego4Screen(navController)
        }

        // Juego 4 opciones
        composable("Juego4.1Colores") {
            Colores(navController)
        }

        composable("Juego4.2Numeros") {
            Numeros(navController)
        }

        composable("Juego4.3Resta") {
            Resta(navController)
        }

        composable("Juego4.4Suma") {
            Suma(navController)
        }

    }

}