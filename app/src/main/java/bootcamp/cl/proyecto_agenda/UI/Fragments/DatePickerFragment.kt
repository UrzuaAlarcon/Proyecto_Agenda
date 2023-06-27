package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import kotlin.time.Duration.Companion.days

class DatePickerFragment(val listener: (day: Int, month: Int, year: Int) -> Unit) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfTheMonth: Int) {
        // Call the listener with the selected date components
        listener(dayOfTheMonth, month, year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Get the current date from the calendar
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        // Create a DatePickerDialog with the current date and set the listener
        val picker = DatePickerDialog(requireContext(), this, year, month, day)

        // Set the minimum selectable date to the current date
        picker.datePicker.minDate = calendar.timeInMillis

        return picker
    }
}