package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Meds(

    //data for this database entity

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var medsName: String,
    var medsIndication: String,
    val userId: Int=0
)
