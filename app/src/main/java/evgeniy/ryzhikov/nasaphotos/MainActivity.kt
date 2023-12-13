package evgeniy.ryzhikov.nasaphotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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

//        navController.addOnDestinationChangedListener{ _, destination, _ ->
//            println("destination $destination")
//            println("destination.id ${destination.id} R.id.splashFragment ${R.id.splashFragment}")
////             = destination.id != R.id.splashFragment
//        }
    }

}