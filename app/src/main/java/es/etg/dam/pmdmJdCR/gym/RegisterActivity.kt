package es.etg.dam.pmdmJdCR.gym

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

        val savedUsername = sharedPref.getString("USERNAME", "")
        if (!savedUsername.isNullOrEmpty()) {
            binding.usernameEditText.setText(savedUsername)
        }

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
}
