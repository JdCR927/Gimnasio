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

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtFldUserName: EditText = findViewById(R.id.txtFld_userName)
        val btnRegister: Button = findViewById(R.id.btn_register)
        val btnLogin: Button = findViewById(R.id.btn_login)

        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPref.getString("USERNAME", "")
        if (!savedUsername.isNullOrEmpty()) {
            txtFldUserName.setText(savedUsername)
        }

        btnRegister.setOnClickListener { view ->
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

        btnLogin.setOnClickListener { view ->
            val username = txtFldUserName.text.toString()

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