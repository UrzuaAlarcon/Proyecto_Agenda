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

    var context = this
    lateinit var navController: NavController
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) { setTheme(R.style.Theme_Proyecto_Agenda)
        Thread.sleep(2000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this,R.id.fragmentContainerView)



    }

    override fun onSupportNavigateUp(): Boolean {

        return NavigationUI.navigateUp(navController,null)
    }

}