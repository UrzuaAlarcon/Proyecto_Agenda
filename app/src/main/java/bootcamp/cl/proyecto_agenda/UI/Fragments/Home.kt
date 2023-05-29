package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentHomeBinding

import com.google.firebase.auth.FirebaseAuth


class Home : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferences = context?.getSharedPreferences(getString
            (R.string.preferences_file),Context.MODE_PRIVATE)
        val uid = preferences?.getString("UID",null)

        if (uid != null){
            findNavController().navigate(R.id.action_home2_to_main_Fragment2)
        }

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


        binding.btnCreateAccount.setOnClickListener {

            findNavController().navigate(R.id.action_home2_to_createAccount2)

        }

        binding.btnLogin.setOnClickListener {

            if (binding.singInUserName.text!!.isNotEmpty() &&
                binding.SingInUserPassword.text!!.isNotEmpty()) {

                singIn()

              } else {

                showAlert()
            }
        }
    }

    //singIn con usuario registrado de firebase
    private fun singIn(){

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(
                binding.singInUserName.text.toString(),
                binding.SingInUserPassword.text.toString()
            ).addOnCompleteListener {

                if (it.isSuccessful) {
                    val UID = it.result.user?.uid

                    saveLogInUserSession(UID)

                    findNavController().navigate(R.id.action_home2_to_main_Fragment2)

                } else {

                    showAlert()

                }
            }
    }

    //guardar el login del user en un shared preferences
    private fun saveLogInUserSession(UID:String?){

        val preferences = context?.getSharedPreferences(getString
            (R.string.preferences_file),Context.MODE_PRIVATE)?.edit()
        preferences?.putString("UID" , UID.toString())
        preferences?.apply()
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