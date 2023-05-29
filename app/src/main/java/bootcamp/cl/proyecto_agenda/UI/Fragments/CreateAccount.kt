package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.AgendaApp
import bootcamp.cl.proyecto_agenda.Models.User
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date

class CreateAccount : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding

    val app = requireContext().applicationContext as AgendaApp
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveCreateAccount.setOnClickListener {

            if (binding.userEmail.text!!.isNotEmpty() && binding.userPassword.text!!.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        binding.userEmail.text.toString(),
                        binding.userPassword.text.toString()
                    ).addOnCompleteListener {

                        if (it.isSuccessful) {
                            val UID = it.result.user?.uid

                            saveCreatedUserSession(UID)

                          //  createNewUser()

                            findNavController().navigate(R.id.action_createAccount2_to_main_Fragment2)
                        } else {
                            showAlert()
                        }
                    }
            } else {

                showAlert()
            }

            //Agregar usuario a la base de datos

            //crear un hilo para las acciones con la base de datos

            /*lifecycleScope.launch(Dispatchers.IO){

                app.room.userDao().insertUser()

            }*/



        }
    }

    private fun saveCreatedUserSession(UID:String?){

        val preferences = context?.getSharedPreferences(getString
            (R.string.preferences_file), Context.MODE_PRIVATE)?.edit()
        preferences?.putString("UID" , UID.toString())
        preferences?.apply()
    }
    private fun showAlert(){

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Error al intentar registrar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog:AlertDialog = builder.create()
        dialog.show()
    }

/*    private fun createNewUser( name:String, mail:String, age:Int):User{

        var newUser = User(id = null,name = binding.userName.text.toString(),
            mail = binding.userEmail.text.toString(), age = binding.userAge.text.toString().toInt())

        return newUser

    }*/

}



