package es.etg.dam.pmdmJdCR.gym.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
class UsuarioEntity {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
    var usuario:String = ""
    var correo:String = ""
}