package bootcamp.cl.proyecto_agenda.Interfaces

import android.view.View
import androidx.navigation.NavController
import bootcamp.cl.proyecto_agenda.DataBase.UserDao

interface AccountPresenter {
    /**
     * Creates a new user account with the provided email and password.
     * Navigates to the specified NavController upon completion.
     * Displays a visual representation of the view.
     */
    fun createNewAccount(
        name:String,
        mail: String?,
        password: String?,
        navController: NavController,
        view: View
    )

    /**
     * Adds the user to the data base
     */
    fun addNewUserToDataBase(
        userDao: UserDao,
        uid:String,
        mail:String,
        name:String

    )

    /**
     * Saves the session state for the user with the provided UID.
     */
    fun saveSessionState(uid: String?)

    /**
     * Shows an alert to the user.
     */
    fun showAlert()
}