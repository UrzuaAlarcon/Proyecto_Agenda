package bootcamp.cl.proyecto_agenda.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import bootcamp.cl.proyecto_agenda.Models.NextAppointmentData
import bootcamp.cl.proyecto_agenda.databinding.ItemNextAppointmentsBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NextAppointmentsAdapter(private val context: Context, private val NAList: List<NextAppointmentData>)
    : ArrayAdapter<NextAppointmentData>(context, 0, NAList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Inflate the layout for each item in the ListView
        val inflater = LayoutInflater.from(context)
        val binding = ItemNextAppointmentsBinding.inflate(inflater, parent, false)

        // Get the next appointment from the list
        val nextAppointment = NAList[position]

        // Split the date and time using "T" as the separator
        val dateTimeSplit = nextAppointment.dateAndTime.toString().split("T")

        // Format the date and time separately
        var date = formatDate(dateTimeSplit.first())
        var time = dateTimeSplit.last()

        // Set the specialty, date and time, and location in the layout's TextViews
        binding.NASpecialty.text = nextAppointment.specialty
        binding.NADateAndTime.text = "Date: $date Time: $time"
        binding.NALocation.text = nextAppointment.location

        return binding.root

    }

    // Formats the received date to a specific format.
    fun formatDate(receivedDate: String): String {
        val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatterOutput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(receivedDate, formatterInput)
        return date.format(formatterOutput)
    }
}