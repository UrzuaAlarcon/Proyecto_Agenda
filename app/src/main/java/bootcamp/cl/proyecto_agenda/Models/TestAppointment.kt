package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["uid"], childColumns = ["userId"])
])
data class TestAppointment (

    //data for this database entity
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val testType:String,
    val dateAndTime: LocalDateTime,
    val location:String,
    val indication:String,
    val userId:String
)