package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.navigation.NavController
import bootcamp.cl.proyecto_agenda.Interfaces.AccountPresenter
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.CreateAccount
import com.google.firebase.auth.FirebaseAuth

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
                .addOnFailureListener { showAlert() }
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

    override fun showAlert() {

        val builder = AlertDialog.Builder(createAccount.context)
        builder.setTitle("Error")
        builder.setMessage("Error al intentar registrar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}
