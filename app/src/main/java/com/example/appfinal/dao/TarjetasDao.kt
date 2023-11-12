package com.example.appfinal.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appfinal.model.Categorias
import com.example.appfinal.model.Images
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagesDao {
    @Insert
    suspend fun saveImageReference(image: Images)
    @Query("Select * from images")
    fun getAllImages(): Flow<List<Images>>
    @Query("SELECT * FROM images WHERE category = :category")
    fun getImagesByCategory(category: String): Flow<List<Images>>
}

@Dao
interface CategoriasDao {
    @Insert
    suspend fun insertCategoria(textEntity: Categorias)
    @Query("SELECT * FROM Categorias")
    fun getAllCategorias(): Flow<List<Categorias>>
}