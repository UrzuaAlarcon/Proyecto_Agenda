package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.DataBase.DocAppointmentDao
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.Models.NextAppointmentData
import java.time.LocalDateTime

interface NewDocAppointmentPresenter {
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

    /**
     * Adds a doctor's appointment to the database.
     * @param docAppointmentDao The DocAppointmentDao object to access the database.
     * @param doctorName The name of the doctor.
     * @param specialty The doctor's specialty.
     * @param dateAndTime The appointment date and time as a LocalDateTime object.
     * @param location The location of the appointment.
     */
    fun addDocAppointmentToDataBase(
        docAppointmentDao: DocAppointmentDao,
        doctorName: String,
        specialty: String,
        dateAndTime: LocalDateTime,
        location: String,
        userId:String
    )

    /**
     * Shows an alert.
     */
    fun showAlert()

    /**
     * Retrieves all doctor appointments from the database.
     * @param docAppointmentDao The DocAppointmentDao object to access the database.
     * @return A list of DocAppointment objects representing the doctor appointments.
     */
    suspend fun getAllDocs(docAppointmentDao: DocAppointmentDao?, uid:String): List<DocAppointment>
}