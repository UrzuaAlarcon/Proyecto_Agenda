package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import kotlin.time.Duration.Companion.days

class DatePickerFragment(val listener: (day:Int, month:Int, year:Int) -> Unit): DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfTheMonth: Int) {

        listener(dayOfTheMonth, month, year)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)



        val picker = DatePickerDialog(requireContext(), this, year, month, day)
        picker.datePicker.minDate = calendar.timeInMillis
        return picker

    }
}