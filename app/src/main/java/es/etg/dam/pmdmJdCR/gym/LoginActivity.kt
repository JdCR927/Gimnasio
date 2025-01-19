package es.etg.dam.pmdmJdCR.gym

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityLoginBinding
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val savedUsername = sharedPref.getString("USERNAME", "")
        if (!savedUsername.isNullOrEmpty()) {
            binding.usernameEditText.setText(savedUsername)
        }

        binding.loginButton.setOnClickListener {
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

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()

            if (username.isNotBlank()) {
                with(sharedPref.edit()) {
                    putString("USERNAME", username)
                    apply()
                }
            }

            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }
    }
}
