package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import androidx.navigation.NavController
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.PREFERENCES_KEY
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.UserDao
import bootcamp.cl.proyecto_agenda.Interfaces.AccountPresenter
import bootcamp.cl.proyecto_agenda.Models.User
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.CreateAccount
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateAccountPresenter(private val createAccount: CreateAccount) : AccountPresenter {

    override fun createNewAccount(
        name: String,
        mail: String?,
        password: String?,
        navController: NavController,
        view: View
    ) {
        // Find the email and name EditText views from the provided view
        val userMail: EditText = view.findViewById(R.id.userEmail)
        val userName: EditText = view.findViewById(R.id.userName)

        // Get the instance of the AgendaDb database
        val agendaDb: AgendaDb = AgendaDb.getDataBase(createAccount.requireContext())

        // Get the UserDao from the AgendaDb
        val userDao = agendaDb.userDao()

        // Create a handler for running code on the main thread
        val handler = Handler(Looper.getMainLooper())

        if (name.isNotEmpty() && mail?.isNotEmpty() == true && password?.isNotEmpty() == true) {

            // Create a new user with the provided email and password using FirebaseAuth
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener {

                    val uid = it.result.user?.uid

                    // Save the session state with the obtained UID
                    saveSessionState(uid)

                    if (it.isSuccessful) {
                        // Navigate to the main Fragment using the provided NavController
                        navController.navigate(R.id.action_createAccount2_to_main_Fragment2)

                        // Add a new user to the database with the provided UserDao
                        handler.postDelayed(
                            {
                                addNewUserToDataBase(
                                    userDao,
                                    createAccount.getUid(),
                                    userMail.text.toString(),
                                    userName.text.toString()
                                )
                            },
                            2000
                        )

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
        // Get the SharedPreferences editor for the preferences file
        val preferences = createAccount.context?.getSharedPreferences(
            "bootcamp.cl.proyecto_Agenda.PREFERENCES_FILE_KEY", Context.MODE_PRIVATE
        )?.edit()

        // Put the UID into the preferences file
        preferences?.putString(PREFERENCES_KEY, uid.toString())
        preferences?.apply()
    }

    override fun addNewUserToDataBase(userDao: UserDao, uid: String, mail: String, name: String) {
        // Create a new User instance with the provided values
        val user = User(
            uid = uid,
            mail = mail,
            name = name
        )

        runBlocking {
            launch(Dispatchers.IO) {
                // Insert the user into the database using UserDao
                userDao.insertUser(user)
            }
        }
    }

    override fun showAlert() {
        // Create an AlertDialog to display an error message
        val builder = AlertDialog.Builder(createAccount.context)
        builder.setTitle("Error")
        builder.setMessage("Error al intentar registrar al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
