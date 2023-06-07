package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.Interfaces.AccountPresenter
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth


class CreateAccount : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding
    //private val presenter = CreateAccountPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = CreateAccountPresenter(this)

        binding.btnSaveCreateAccount.setOnClickListener {

            presenter.createNewAccount(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString(),
                findNavController(),
                view
            )
        }
    }
}

class CreateAccountPresenter(private val createAccount: CreateAccount) : AccountPresenter {

    override fun createNewAccount(
        mail: String?,
        password: String?,
        navController: NavController,
        view: View
    ) {

        if (mail!!.isNotEmpty() && password!!.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    mail,
                    password
                ).addOnCompleteListener {

                    val uid = it.result.user?.uid

                    saveSessionState(uid)

                    if (it.isSuccessful) {
                        navController.navigate(R.id.action_createAccount2_to_main_Fragment2)
                    } else {
                        showAlert()
                    }
                }
        } else {
            showAlert()
        }
    }

    override fun saveSessionState(uid: String?) {

        val preferences = createAccount.context?.getSharedPreferences(
            "bootcamp.cl.proyecto_Agenda.PREFERENCES_FILE_KEY", Context.MODE_PRIVATE
        )?.edit()
        preferences?.putString("UID", uid.toString())
        preferences?.apply()

    }

    private fun showAlert() {

        val builder = AlertDialog.Builder(createAccount.context)
        builder.setTitle("Error")
        builder.setMessage("Error al intentar registrar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }



}




