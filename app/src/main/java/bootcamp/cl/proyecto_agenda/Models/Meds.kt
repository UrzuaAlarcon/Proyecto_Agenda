package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Meds(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var medsName: String,
    var medsIndication: String,
    val userId: Int=0
)
