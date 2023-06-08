package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.navigation.NavController
import bootcamp.cl.proyecto_agenda.Interfaces.HomePresenter
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.Home
import com.google.firebase.auth.FirebaseAuth

class HomePresenterIMPL(private val home: Home):HomePresenter {
    override fun singIn(
        mail: String?,
        password: String?,
        navController: NavController,
        view: View
    ) {
        //singIn con usuario registrado de firebase
        if (mail!!.isNotEmpty() && password!!.isNotEmpty()){

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(
                   mail,
                    password
                ).addOnCompleteListener {

                    if (it.isSuccessful) {
                        val uid = it.result.user?.uid

                        saveSessionState(uid)

                        navController.navigate(R.id.action_home2_to_main_Fragment2)

                    } else {

                        showAlert()

                    }
                }

        }else{

            showAlert()

        }


    }

    override fun saveSessionState(uid: String?) {

        val preferences = home.context?.getSharedPreferences(
            "bootcamp.cl.proyecto_Agenda.PREFERENCES_FILE_KEY",
            Context.MODE_PRIVATE)?.edit()
        preferences?.putString("UID" , uid.toString())
        preferences?.apply()

    }

    override fun showAlert() {
        val builder = AlertDialog.Builder(home.context)
        builder.setTitle("Error")
        builder.setMessage("Error de autenticacion")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}