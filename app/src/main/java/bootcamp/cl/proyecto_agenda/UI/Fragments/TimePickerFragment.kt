package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment(val listener: (String) -> Unit) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Get the current time from the system
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Create a TimePickerDialog with the current time as the default selection
        val dialog = TimePickerDialog(requireContext(), this, hour, minute, true)

        return dialog
    }

    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        // When the time is set, invoke the listener and pass the selected time as a formatted string
        listener("$hourOfDay:$minute")
    }
}