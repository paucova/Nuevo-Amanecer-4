package com.example.appfinal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appfinal.dao.CategoriasDao
import com.example.appfinal.dao.ImagesDao
import com.example.appfinal.model.Categorias
import com.example.appfinal.model.Images

@Database(entities = [Images::class, Categorias::class], version = 1)
abstract class TarjetasDatabase : RoomDatabase() {
    abstract val daoImg: ImagesDao
    abstract val daoCat: CategoriasDao
}