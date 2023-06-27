package bootcamp.cl.proyecto_agenda.DataBase

import android.content.Context
import androidx.fragment.app.Fragment

object ConstantUtil {

    const val PREFERENCES_KEY = "UID"

    fun Fragment.getUid() = context?.getSharedPreferences("bootcamp.cl.proyecto_Agenda.PREFERENCES_FILE_KEY",
        Context.MODE_PRIVATE)?.getString(PREFERENCES_KEY, "") ?: ""

}