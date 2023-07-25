package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.DataBase.TestAppointmentDao
import bootcamp.cl.proyecto_agenda.Models.TestAppointment
import java.time.LocalDateTime

interface NewTestAppointmentPresenter {

    /**
     * Displays the time picker dialog.
     */
    fun showTimePickerDialog()

    /**
     * Called when a time is selected.
     * @param time The selected time as a string.
     */
    fun onTimeSelected(time: String)

    /**
     * Displays the date picker dialog.
     */
    fun showDatePickerDialog()

    /**
     * Called when a date is selected.
     * @param day The selected day.
     * @param month The selected month.
     * @param year The selected year.
     */
    fun onDateSelected(day: Int, month: Int, year: Int)

    /**
     * Saves the selected date and time.
     * @return The LocalDateTime object representing the selected date and time.
     */
    fun saveDateAndTimeSelected(): LocalDateTime

    /**
     * Formats the selected date into a specific format.
     * @param dateSelected The selected date as a string.
     * @return The formatted date in the desired format.
     */
    fun formatDate(dateSelected: String): String


    fun addTestAppointmentToDataBase(
        testAppointmentDao: TestAppointmentDao,
        testType: String,
        dateAndTime: LocalDateTime,
        location: String,
        indications:String,
        userId:String
    )

    /**
     * Shows an alert.
     */
    fun showAlert()

    suspend fun getAllTestAppointments(testAppointmentDao: TestAppointmentDao?, uid:String):
    List<TestAppointment>
}