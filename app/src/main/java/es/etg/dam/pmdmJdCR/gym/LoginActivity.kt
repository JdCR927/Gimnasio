package es.etg.dam.pmdmJdCR.gym

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPref.getString("USERNAME", "")
        if (!savedUsername.isNullOrEmpty()) {
            txtFldUserName.setText(savedUsername)
        }

        btnLogin.setOnClickListener {
            val username = txtFldUserName.text.toString()

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

        btnRegister.setOnClickListener {
            val username = txtFldUserName.text.toString()

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