package bootcamp.cl.proyecto_agenda.Models

import java.time.LocalDateTime

data class DocAppointment(

     val id:Int = 0,
     val doctorName:String,
     val specialty:String,
     val dateAndTime:LocalDateTime,
     val location:String

 )