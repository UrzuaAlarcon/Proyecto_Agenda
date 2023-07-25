package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import bootcamp.cl.proyecto_agenda.DataBase.TestAppointmentDao
import bootcamp.cl.proyecto_agenda.Interfaces.NewTestAppointmentPresenter
import bootcamp.cl.proyecto_agenda.Models.TestAppointment
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.DatePickerFragment
import bootcamp.cl.proyecto_agenda.UI.Fragments.NewTestAppointmentFragment
import bootcamp.cl.proyecto_agenda.UI.Fragments.TimePickerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewTestAppointmentPresenterImpl(private val newTestAppointment: NewTestAppointmentFragment):
NewTestAppointmentPresenter{

    override fun showTimePickerDialog() {
        val timePicker = TimePickerFragment { onTimeSelected(it) }
        timePicker.show(newTestAppointment.childFragmentManager, "Time")
    }

    override fun onTimeSelected(time: String) {
        formateTimeSelected(time)
    }

    override fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(newTestAppointment.parentFragmentManager, "datePicker")
    }

    override fun onDateSelected(day: Int, month: Int, year: Int) {
        formateDateSelected(day, month, year)
    }

    override fun saveDateAndTimeSelected(): LocalDateTime {
        var selectedDate = newTestAppointment.binding.newDate.text.toString().trim()
        var selectedTime = newTestAppointment.binding.newTime.text.toString().trim()

        var formatedDate = formatDate(selectedDate)

        // String that represents the selected date and time
        var dateAndTimeFormat = "$formatedDate" + "T" + "$selectedTime"

        var dateAndTime =
            LocalDateTime.parse(dateAndTimeFormat, DateTimeFormatter.ISO_LOCAL_DATE_TIME)

        return dateAndTime
    }

    override fun formatDate(dateSelected: String): String {
        val formatterInput = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateSelected, formatterInput)
        return date.format(formatterOutput)
    }

    override fun addTestAppointmentToDataBase(
        testAppointmentDao: TestAppointmentDao,
        testType: String,
        dateAndTime: LocalDateTime,
        location: String,
        indications: String,
        userId: String
    ) {
        val testAppointment = TestAppointment(
            testType = testType,
            dateAndTime = dateAndTime,
            location = location,
            indication = indications,
            userId = userId
        )

        runBlocking {

            launch(Dispatchers.IO) {

                testAppointmentDao.inserTestAppointment(testAppointment)

            }

        }

        showAlert()

    }

    override fun showAlert() {
        val builder = AlertDialog.Builder(newTestAppointment.context)
        builder.setTitle(newTestAppointment.getString(R.string.guardado_exitoso))
        builder.setMessage(newTestAppointment.context?.getString(R.string.cita_examen_guardada))
        builder.setPositiveButton(newTestAppointment.context?.getString(R.string.aceptar), null)

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override suspend fun getAllTestAppointments(
        testAppointmentDao: TestAppointmentDao?,
        uid: String
    ): List<TestAppointment> {

        return testAppointmentDao?.getAll(uid)?.toList() ?: emptyList()

    }

    private fun formateTimeSelected(time: String) {
        val timeF = time.split(":")

        val hour = timeF.first().toInt()
        val minutes = timeF.last().toInt()

        if (hour < 10 || minutes < 10) {
            if (hour < 10 && minutes < 10) {
                newTestAppointment.binding.newTime.setText("0$hour:0$minutes:00")
            }
            if (hour < 10 && minutes > 10) {
                newTestAppointment.binding.newTime.setText("0$hour:$minutes:00")
            }
            if (hour > 10 && minutes < 10) {
                newTestAppointment.binding.newTime.setText("$hour:0$minutes:00")
            }
        } else {
            newTestAppointment.binding.newTime.setText("$time:00")
        }
    }

    private fun formateDateSelected(day: Int, month: Int, year: Int) {
        if (day < 10 || month < 10) {
            if ((day < 10 && month < 10)) {
                newTestAppointment.binding.newDate.setText(" 0$day-0${month.plus(1)}-$year")
            }
            if (day < 10 && month > 10) {
                newTestAppointment.binding.newDate.setText(" 0$day-${month.plus(1)}-$year")
            }
            if (day > 10 && month < 10) {
                newTestAppointment.binding.newDate.setText(" $day-0${month.plus(1)}-$year")
            }
        } else {
            newTestAppointment.binding.newDate.setText(" $day-${month.plus(1)}-$year")
        }
    }

}