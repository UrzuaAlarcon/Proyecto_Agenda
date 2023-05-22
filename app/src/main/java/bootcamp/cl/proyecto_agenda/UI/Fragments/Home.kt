package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class Home : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding

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

        binding.btnCreateAccount.setOnClickListener {

            findNavController().navigate(R.id.action_home2_to_createAccount2)

        }

        binding.btnLogin.setOnClickListener {

            if (binding.singInUserName.text!!.isNotEmpty() && binding.SingInUserPassword.text!!.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        binding.singInUserName.text.toString(),
                        binding.SingInUserPassword.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {

                            findNavController().navigate(R.id.action_home2_to_main_Fragment2)
                        } else {

                            showAlert()

                        }

                    }
            } else {

                showAlert()
            }
        }
    }

    private fun showAlert(){

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Error de autenticacion")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}