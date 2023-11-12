package com.example.appfinal.viewModel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinal.model.Categorias
import com.example.appfinal.NuevoAmanecerApp
import com.example.appfinal.model.Images
import kotlinx.coroutines.launch

class TarjetasViewModel(application: Application) : AndroidViewModel(application) {
    val images = mutableStateOf<List<Images>>(emptyList())
    val categories = mutableStateOf<List<Categorias>>(emptyList())

    private val database = (application as NuevoAmanecerApp).database
    private val daoImagenes = database.daoImg
    private val daoCategorias = database.daoCat

    init{
        viewModelScope.launch {
            daoImagenes.getAllImages().collect() {
                images.value = it
            }
        }
        viewModelScope.launch {
            daoCategorias.getAllCategorias().collect(){
                categories.value = it
            }
        }
    }
    fun saveImageReference(name: String, category: String, text: String, filePath:Int) {
        val image = Images(0,name, category, text, filePath)
        viewModelScope.launch {
            daoImagenes.saveImageReference(image)
        }
    }
    fun nuevaCategoria(text: String){
        val categ = Categorias(0,text)
        viewModelScope.launch {
            daoCategorias.insertCategoria(categ)
        }
    }
}