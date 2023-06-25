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

class NextAppointmentsAdapter (private val context: Context, private val NAList : List<NextAppointmentData>)
    :ArrayAdapter<NextAppointmentData>(context,0,NAList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(context)
        val binding = ItemNextAppointmentsBinding.inflate(inflater,parent,false)

        val nextAppointment = NAList[position]
        val dateTimeSplit = nextAppointment.dateAndTime.toString().split("T")
        var date = formatDate(dateTimeSplit.first())
        var time = dateTimeSplit.last()

      binding.NASpecialty.text = nextAppointment.specialty
        binding.NADateAndTime.text = "Fecha: $date Hora: $time"
        binding.NALocation.text = nextAppointment.location

        return binding.root

    }

    // Formats the received date  to a specific format.
    fun formatDate(receivedDate: String): String {
        val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatterOutput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(receivedDate, formatterInput)
        return date.format(formatterOutput)
    }
}