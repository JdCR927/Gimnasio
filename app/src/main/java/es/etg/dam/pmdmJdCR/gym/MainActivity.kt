package es.etg.dam.pmdmJdCR.gym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import es.etg.dam.pmdmJdCR.gym.data.db.UsuarioDatabase
import es.etg.dam.pmdmJdCR.gym.data.fragment.RecyclerViewFragment
import es.etg.dam.pmdmJdCR.gym.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: UsuarioDatabase
        const val DATABASE_NAME = "usuario-db"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            this,
            UsuarioDatabase::class.java,
            DATABASE_NAME
        ).build()

        val username = intent.getStringExtra("USERNAME") ?: getString(R.string.default_username)

        binding.welcomeLabel.text = getString(R.string.lbl_welcome, username)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, RecyclerViewFragment())
            .commit()
    }
}
