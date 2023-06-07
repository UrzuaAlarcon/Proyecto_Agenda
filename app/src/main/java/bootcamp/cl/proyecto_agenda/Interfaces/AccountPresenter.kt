package bootcamp.cl.proyecto_agenda.Interfaces

import android.view.View
import androidx.navigation.NavController

interface AccountPresenter {

    fun createNewAccount(
        mail: String?,
        password: String?,
        showAlert: Unit, navController: NavController, view : View
    ):Boolean

    fun saveSessionState(UID: String?)

}