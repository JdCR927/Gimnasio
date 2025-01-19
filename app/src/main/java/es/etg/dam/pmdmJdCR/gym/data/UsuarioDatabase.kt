package es.etg.dam.pmdmJdCR.gym.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class UsuarioDatabase: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDAO
}