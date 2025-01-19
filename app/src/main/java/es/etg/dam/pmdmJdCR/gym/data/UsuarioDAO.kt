package es.etg.dam.pmdmJdCR.gym.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDAO {

    @Query("SELECT id, usuario, correo FROM usuario")
    suspend fun getAll(): List<UsuarioEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: UsuarioEntity)

    @Delete
    suspend fun delete(usuario: UsuarioEntity)
}