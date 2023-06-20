package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import bootcamp.cl.proyecto_agenda.DataBase.DocAppointmentDao
import bootcamp.cl.proyecto_agenda.Interfaces.NewDocAppointmentPresenter
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.DatePickerFragment
import bootcamp.cl.proyecto_agenda.UI.Fragments.NewDocAppoitmentFragment
import bootcamp.cl.proyecto_agenda.UI.Fragments.TimePickerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewDocAppointmentsPresenterImpls(private val newDoc: NewDocAppoitmentFragment)
    : NewDocAppointmentPresenter {

    override fun showTimePickerDialog() {
        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(newDoc.childFragmentManager, "Time")
    }
    override fun onTimeSelected(time: String) {
        newDoc.binding.newTime.setText("$time:00")
    }

    override fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(newDoc.parentFragmentManager,"datePicker")
    }

    override fun onDateSelected(day: Int, month: Int, year: Int) {
        newDoc.binding.newDate.setText(" $day-$month-$year")
    }

    override fun saveDateAndTimeSelected(): LocalDateTime {
        var selectedDate = newDoc.binding.newDate.text.toString()
        var selectedTime = newDoc.binding.newTime.text.toString()

        var formatedDate = formatDate(selectedDate)

        //String que representa la fecha y hora seleccionada
        var dateAndTimeFormat = "$formatedDate $selectedTime"

        var dateAndTime = LocalDateTime.parse(dateAndTimeFormat)

        return dateAndTime
    }

    override fun formatDate(dateSelected: String): String {
        val formatterInput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateSelected, formatterInput)
        return date.format(formatterOutput)
    }

    override fun addDocAppointmentToDataBase(
        docAppointmentDao: DocAppointmentDao,
        doctorName: String,
        specialty: String,
        dateAndTime: LocalDateTime,
        location: String
    ) {

        val docAppointment = DocAppointment(
            doctorName= doctorName,
            specialty = specialty,
            dateAndTime = dateAndTime,
            location = location)

        runBlocking {

            launch(Dispatchers.IO) {

                docAppointmentDao?.insertDocAppointment(docAppointment)

            }

        }

    }

    override fun showAlert() {

        val builder = AlertDialog.Builder(newDoc.context)
        builder.setTitle(newDoc.getString(R.string.guardado_exitoso))
        builder.setMessage(newDoc.context?.getString(R.string.cita_medica_guardada))
        builder.setPositiveButton(newDoc.context?.getString(R.string.aceptar), null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override suspend fun getAllDocs(docAppointmentDao: DocAppointmentDao?)
    :List<DocAppointment> {

        return docAppointmentDao?.getAll()?.toList() ?: emptyList()

    }


}