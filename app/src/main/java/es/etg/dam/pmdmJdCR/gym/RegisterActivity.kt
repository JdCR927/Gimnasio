package es.etg.dam.pmdmJdCR.gym

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import es.etg.dam.pmdmJdCR.gym.data.db.UsuarioDatabase
import es.etg.dam.pmdmJdCR.gym.data.db.UsuarioEntity
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    companion object {
        lateinit var database: UsuarioDatabase
        const val DATABASE_NAME = "usuario-db"
        const val FILL_SPACE = "Por favor, rellena todos los campos."
    }

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database =  Room.databaseBuilder(this,
            UsuarioDatabase::class.java,
            DATABASE_NAME)
        .build()

        binding.registerButton.setOnClickListener {
            registrar()
        }

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrar() {
        val usuario: String = binding.usernameEditText.text.toString()
        val correo: String = binding.emailEditText.text.toString()
        val pass: String = binding.passwordEditText.text.toString()
        val confirmPass: String = binding.confirmPasswordEditText.text.toString()

        if (usuario.isBlank() || correo.isBlank() || pass.isBlank() || confirmPass.isBlank()) {
            Toast.makeText(this, FILL_SPACE, Toast.LENGTH_SHORT).show()
            return // Detiene la ejecucion
        }

        val usuarioEntity = UsuarioEntity(0, usuario, correo)
        val usuarioDao = database.usuarioDao()

        CoroutineScope(Dispatchers.IO).launch {
            usuarioDao.insert(usuarioEntity)

            binding.usernameEditText.text.clear()
            binding.emailEditText.text.clear()
            binding.passwordEditText.text.clear()
            binding.confirmPasswordEditText.text.clear()

            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            intent.putExtra("USERNAME", usuario)
            startActivity(intent)
        }
    }
}
