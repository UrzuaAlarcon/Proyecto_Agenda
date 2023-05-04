package bootcamp.cl.proyecto_agenda

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.databinding.FragmentHomeBinding


class Home : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding= DataBindingUtil.setContentView(Activity(), R.layout.fragment_home)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        var btnLogin:Button = dataBinding.btnLogin
        var btnCreateAccount:Button = dataBinding.btnCreateAccount

        val navControler:NavController = Navigation.findNavController(view)

        btnLogin.setOnClickListener {


                navControler.navigate(R.id.login2)

        }

        btnCreateAccount.setOnClickListener {

            navControler.navigate(R.id.createAccount2)

        }


    }

    companion object {
    }
}