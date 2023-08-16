package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
    ForeignKey(entity = User::class, parentColumns = ["uid"], childColumns = ["userId"])
])
data class DocIndications (

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val diagnostic:String,
    val indication:String?,
    val imagePath:String,
    val userId:String
        )