package bootcamp.cl.proyecto_agenda.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentHomeBinding


class Home : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        var btnLogin: Button = binding.btnLogin
        var btnCreateAccount: Button = binding.btnCreateAccount


        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_login2)
        }

        btnCreateAccount.setOnClickListener {

            Toast.makeText(context, "Me hicieron click", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_home2_to_createAccount2)

        }


    }

}