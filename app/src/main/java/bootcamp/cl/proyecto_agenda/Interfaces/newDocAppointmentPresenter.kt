package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.DataBase.DocAppointmentDao
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import java.time.LocalDateTime

interface NewDocAppointmentPresenter {

    fun showTimePickerDialog()
    fun onTimeSelected(time: String)
    fun showDatePickerDialog()

    fun onDateSelected(day:Int, month:Int, year:Int)

    fun saveDateAndTimeSelected(): LocalDateTime

    fun formatDate(dateSelected:String):String

    fun addDocAppointmentToDataBase (
        docAppointmentDao: DocAppointmentDao,
        doctorName:String,
        specialty:String,
        dateAndTime:LocalDateTime,
        location:String)

    fun showAlert()

    suspend fun getAllDocs(docAppointmentDao: DocAppointmentDao?)
    :List<DocAppointment>

}