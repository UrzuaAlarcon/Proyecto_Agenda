package bootcamp.cl.proyecto_agenda.Interfaces

import android.view.View
import androidx.navigation.NavController

interface AccountPresenter {

    fun createNewAccount(
        mail: String?,
        password: String?, navController: NavController, view : View
    )

    fun saveSessionState(uid: String?)

}