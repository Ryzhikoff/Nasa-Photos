package evgeniy.ryzhikov.nasaphotos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import evgeniy.ryzhikov.nasaphotos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomBar.setupWithNavController(navController)

        window.statusBarColor = ContextCompat.getColor(this, evgeniy.ryzhikov.core.R.color.black)
        window.navigationBarColor = ContextCompat.getColor(this,  evgeniy.ryzhikov.core.R.color.black)
    }

}