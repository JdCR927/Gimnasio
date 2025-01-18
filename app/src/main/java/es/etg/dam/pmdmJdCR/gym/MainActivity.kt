package es.etg.dam.pmdmJdCR.gym

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialización de ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar SharedPreferences
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("USERNAME", "")?.takeIf { it.isNotBlank() }
            ?: getString(R.string.default_username)

        // Configurar el texto del label de bienvenida
        binding.welcomeLabel.text = getString(R.string.lbl_welcome, username)
    }
}
