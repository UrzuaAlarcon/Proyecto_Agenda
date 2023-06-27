package bootcamp.cl.proyecto_agenda.DataBase

import android.content.Context
import android.widget.EditText
import androidx.fragment.app.Fragment

object ConstantUtil {

    // Constant for the preferences key
    const val PREFERENCES_KEY = "UID"

    // Extension function for Fragment to get the UID from shared preferences
    fun Fragment.getUid() = context?.getSharedPreferences(
        "bootcamp.cl.proyecto_Agenda.PREFERENCES_FILE_KEY",
        Context.MODE_PRIVATE
    )?.getString(PREFERENCES_KEY, "") ?: ""

    // Extension function for EditText to set an error message
    fun EditText.toError() = apply {
        error = "Campo obligatorio"
    }

}