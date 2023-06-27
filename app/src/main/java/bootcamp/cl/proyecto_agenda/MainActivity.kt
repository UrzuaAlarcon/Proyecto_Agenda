package bootcamp.cl.proyecto_agenda

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import bootcamp.cl.proyecto_agenda.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Declare variables
    var context = this
    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Set the theme for the activity
        setTheme(R.style.Theme_Proyecto_Agenda)

        // Add a delay of 2000 milliseconds (2 seconds)
        Thread.sleep(2000)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the NavController by finding the navigation host fragment
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handle the Up button action in the action bar/toolbar
        return NavigationUI.navigateUp(navController, null)
    }
}