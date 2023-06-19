package bootcamp.cl.proyecto_agenda.Interfaces

import java.time.LocalDateTime

interface newDocAppointmentPresenter {

    fun onTimeSelected(time: String)

    fun onDateSelected(day:Int, month:Int, year:Int)

    fun saveDateAndTimeSelected(): LocalDateTime

    fun formatDate(dateSelected:String):String

}