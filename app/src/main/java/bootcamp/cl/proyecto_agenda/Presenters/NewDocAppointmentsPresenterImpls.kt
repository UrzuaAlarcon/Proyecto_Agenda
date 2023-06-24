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
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

// This class represents a presenter implementation for a new doctor appointment screen.
// It implements the NewDocAppointmentPresenter interface.
class NewDocAppointmentsPresenterImpls(private val newDoc: NewDocAppoitmentFragment) :
    NewDocAppointmentPresenter {

    // Shows a time picker dialog.
    override fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(newDoc.childFragmentManager, "Time")
    }

    // Callback function for when a time is selected.
    override fun onTimeSelected(time: String) {
        formateTimeSelected(time)
    }

    // Shows a date picker dialog.
    override fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(newDoc.parentFragmentManager, "datePicker")
    }

    // Callback function for when a date is selected.
    override fun onDateSelected(day: Int, month: Int, year: Int) {
        formateDateSelected(day, month, year)
    }

    // Saves the selected date and time as LocalDateTime and returns it.
    override fun saveDateAndTimeSelected(): LocalDateTime {
        var selectedDate = newDoc.binding.newDate.text.toString().trim()
        var selectedTime = newDoc.binding.newTime.text.toString().trim()

        var formatedDate = formatDate(selectedDate)

        // String that represents the selected date and time
        var dateAndTimeFormat = "$formatedDate" + "T" + "$selectedTime"

        var dateAndTime =
            LocalDateTime.parse(dateAndTimeFormat, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        return dateAndTime
    }

    // Formats the selected date to a specific format.
    override fun formatDate(dateSelected: String): String {
        val formatterInput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateSelected, formatterInput)
        return date.format(formatterOutput)
    }

    // Adds a doctor appointment to the database.
    override fun addDocAppointmentToDataBase(
        docAppointmentDao: DocAppointmentDao,
        doctorName: String,
        specialty: String,
        dateAndTime: LocalDateTime,
        location: String
    ) {
        val docAppointment = DocAppointment(
            doctorName = doctorName,
            specialty = specialty,
            dateAndTime = dateAndTime,
            location = location
        )

        runBlocking {
            launch(Dispatchers.IO) {
                docAppointmentDao?.insertDocAppointment(docAppointment)
            }
        }

        showAlert()
    }

    // Shows an alert dialog.
    override fun showAlert() {
        val builder = AlertDialog.Builder(newDoc.context)
        builder.setTitle(newDoc.getString(R.string.guardado_exitoso))
        builder.setMessage(newDoc.context?.getString(R.string.cita_medica_guardada))
        builder.setPositiveButton(newDoc.context?.getString(R.string.aceptar), null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    // Retrieves all doctor appointments from the database.
    override suspend fun getAllDocs(docAppointmentDao: DocAppointmentDao?)
            : List<DocAppointment> {
        return docAppointmentDao?.getAll()?.toList() ?: emptyList()
    }

    // Formats the selected date in the appropriate format and sets it in the view.
    private fun formateDateSelected(day: Int, month: Int, year: Int) {
        if (day < 10 || month < 10) {
            if ((day < 10 && month < 10)) {
                newDoc.binding.newDate.setText(" 0$day-0${month.plus(1)}-$year")
            }
            if (day < 10 && month > 10) {
                newDoc.binding.newDate.setText(" 0$day-${month.plus(1)}-$year")
            }
            if (day > 10 && month < 10) {
                newDoc.binding.newDate.setText(" $day-0${month.plus(1)}-$year")
            }
        } else {
            newDoc.binding.newDate.setText(" $day-${month.plus(1)}-$year")
        }
    }

    // Formats the selected time in the appropriate format and sets it in the view.
    private fun formateTimeSelected(time: String) {
        val timeF = time.split(":")

        val hour = timeF.first().toInt()
        val minutes = timeF.last().toInt()

        if (hour < 10 || minutes < 10) {
            if (hour < 10 && minutes < 10) {
                newDoc.binding.newTime.setText("0$hour:0$minutes:00")
            }
            if (hour < 10 && minutes > 10) {
                newDoc.binding.newTime.setText("0$hour:$minutes:00")
            }
            if (hour > 10 && minutes < 10) {
                newDoc.binding.newTime.setText("$hour:0$minutes:00")
            }
        } else {
            newDoc.binding.newTime.setText("$time:00")
        }
    }

    }