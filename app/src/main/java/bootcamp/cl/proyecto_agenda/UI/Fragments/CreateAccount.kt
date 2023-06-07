package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.Interfaces.AccountPresenter
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CreateAccount : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding
    private val presenter = CreateAccountPresenter()

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

            presenter.createNewAccount(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString(),
                showAlert(),
                findNavController(),
                view
            )

            if(presenter.authResult){

                findNavController().navigate(R.id.action_createAccount2_to_main_Fragment2)

                //no esta navegando

            }else{
                showAlert()
            }
        }
    }

    private fun saveCreatedUserSession(UID: String?) {

        val preferences = context?.getSharedPreferences(
            getString
                (R.string.preferences_file), Context.MODE_PRIVATE
        )?.edit()
        preferences?.putString("UID", UID.toString())
        preferences?.apply()
    }

    private fun showAlert() {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Error al intentar registrar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}


class CreateAccountPresenter() : AccountPresenter {

    var authResult = false
    override fun createNewAccount(
        mail: String?,
        password: String?,
        showAlert: Unit,
        navController: NavController,
        view: View
    ): Boolean {

        view.findViewTreeLifecycleOwner()?.lifecycleScope?.launch(IO){

            if (!mail.isNullOrEmpty() && !password.isNullOrEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(
                        mail,
                        password
                    ).addOnSuccessListener {
                            authResult = true
                            val uid = it.user?.uid
                    }
            }
        }

        return authResult

    }

    override fun saveSessionState(UID: String?) {
        TODO("Not yet implemented")
    }

}




