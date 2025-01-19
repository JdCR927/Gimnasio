package es.etg.dam.pmdmJdCR.gym

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import es.etg.dam.pmdmJdCR.gym.RegisterActivity.Companion.database
import es.etg.dam.pmdmJdCR.gym.data.db.UsuarioDatabase
import es.etg.dam.pmdmJdCR.gym.data.db.UsuarioEntity
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess


class LoginActivity : AppCompatActivity() {

    companion object {
        const val DATABASE_NAME = "usuario-db"
        const val FILL_SPACE = "Por favor, rellena todos los campos."
        const val LOCATION_REQUEST_CODE = 101
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database =  Room.databaseBuilder(this,
            UsuarioDatabase::class.java,
            DATABASE_NAME
        ).build()

        if(!comprobarPermiso()) {
            pedirPermisos()
        }

        binding.loginButton.setOnClickListener {
            iniciarSesion()
        }

        binding.registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun comprobarPermiso(): Boolean {
        return ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun pedirPermisos() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                return
            } else {
                exitProcess(0)
            }
        }
    }

    private fun iniciarSesion() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (username.isBlank() || password.isBlank()) {
            Toast.makeText(this, FILL_SPACE, Toast.LENGTH_SHORT).show()
            return // Detiene la ejecucion
        }

        CoroutineScope(Dispatchers.IO).launch {
            val usuarioDao = database.usuarioDao()
            val usuario = usuarioDao.getUserByUsername(username)

            if (usuario == null) {
                usuarioNoExiste()
            } else {
                usuarioExiste(usuario)
            }
        }
    }

    private fun usuarioExiste(usuario: UsuarioEntity) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("USERNAME", usuario.usuario)
        startActivity(intent)
    }

    private fun usuarioNoExiste() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
