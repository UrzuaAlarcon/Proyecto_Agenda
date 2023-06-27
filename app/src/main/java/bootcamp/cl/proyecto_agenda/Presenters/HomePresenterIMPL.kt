package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.navigation.NavController
import bootcamp.cl.proyecto_agenda.Interfaces.HomePresenter
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.Home
import com.google.firebase.auth.FirebaseAuth

class HomePresenterIMPL(private val home: Home) : HomePresenter {

    override fun singIn(mail: String?, password: String?, navController: NavController, view: View) {
        // Sign in with a registered Firebase user
        if (mail?.isNotEmpty() == true && password?.isNotEmpty() == true) {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener {

                    if (it.isSuccessful) {
                        val uid = it.result.user?.uid

                        // Save the session state with the obtained UID
                        saveSessionState(uid)

                        // Navigate to the main Fragment using the provided NavController
                        navController.navigate(R.id.action_home2_to_main_Fragment2)
                    } else {
                        showAlert()
                    }
                }
        } else {
            showAlert()
        }
    }

    override fun saveSessionState(uid: String?) {
        // Get the SharedPreferences editor for the preferences file
        val preferences = home.context?.getSharedPreferences(
            "bootcamp.cl.proyecto_Agenda.PREFERENCES_FILE_KEY",
            Context.MODE_PRIVATE
        )?.edit()

        // Put the UID into the preferences file
        preferences?.putString("UID", uid.toString())
        preferences?.apply()
    }

    override fun showAlert() {
        // Create an AlertDialog to display an error message
        val builder = AlertDialog.Builder(home.context)
        builder.setTitle("Error")
        builder.setMessage("Error de autenticacion")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}