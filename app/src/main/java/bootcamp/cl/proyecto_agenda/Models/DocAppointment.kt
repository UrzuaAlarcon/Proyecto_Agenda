package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class DocAppointment(

     @PrimaryKey(autoGenerate = true)
     val id:Int = 0,
     val doctorName:String,
     val specialty:String,
     val dateAndTime:LocalDateTime,
     val location:String

 )