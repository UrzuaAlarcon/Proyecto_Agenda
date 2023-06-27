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
        name:String,
        mail: String?,
        password: String?,
        navController: NavController,
        view: View
    ) {
        val userMail:EditText = view.findViewById(R.id.userName)
        val userName:EditText = view.findViewById(R.id.userEmail)
        val agendaDb: AgendaDb = AgendaDb.getDataBase(createAccount.requireContext())
        val userDao = agendaDb.userDao()
        val handler = Handler(Looper.getMainLooper())

        if (name!!.isNotEmpty() && mail!!.isNotEmpty() && password!!.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    mail,
                    password
                ).addOnCompleteListener {

                    val uid = it.result.user?.uid

                    saveSessionState(uid)

                    if (it.isSuccessful) {
                        navController.navigate(R.id.action_createAccount2_to_main_Fragment2)

                        handler.postDelayed({addNewUserToDataBase(userDao,
                            userMail.text.toString(),
                            userName.text.toString(),
                            createAccount.getUid()
                        )},2000)

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
        preferences?.putString(PREFERENCES_KEY, uid.toString())
        preferences?.apply()
    }

    override fun addNewUserToDataBase(userDao: UserDao, mail: String, name:String, uid:String) {

        val user = User(

            mail = mail,
            name = name,
            uid = uid
        )

        runBlocking {

            launch(Dispatchers.IO) {
                userDao.insertUser(user)
            }
        }

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
