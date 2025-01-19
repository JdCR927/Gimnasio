package es.etg.dam.pmdmJdCR.gym

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import es.etg.dam.pmdmJdCR.gym.data.UsuarioDAO
import es.etg.dam.pmdmJdCR.gym.data.UsuarioDatabase
import es.etg.dam.pmdmJdCR.gym.data.UsuarioEntity
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    companion object {
        lateinit var database: UsuarioDatabase
        const val DATABASE_NAME = "usuario-db"
    }

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RegisterActivity.database =  Room.databaseBuilder(this,
            UsuarioDatabase::class.java,
            DATABASE_NAME).build()

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()

            if (username.isNotBlank()) {
                with(sharedPref.edit()) {
                    putString("USERNAME", username)
                    apply()
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()

            if (username.isNotBlank()) {
                with(sharedPref.edit()) {
                    putString("USERNAME", username)
                    apply()
                }
            }

            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }
    }

    fun guardar(view: View){

        val usuario: String = binding.usernameEditText.text.toString()
        val correo: String = binding.emailEditText.text.toString()
        val usuarioEntity = UsuarioEntity(0, usuario,correo);
        val usuarioDao = database.usuarioDao()

        usuarioDao.insert(usuarioEntity)

    }
}
