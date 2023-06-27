package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["uid"], childColumns = ["userId"])
])
data class Meds(

    //data for this database entity

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var medsName: String,
    var medsIndication: String,
    val userId:String
    )
