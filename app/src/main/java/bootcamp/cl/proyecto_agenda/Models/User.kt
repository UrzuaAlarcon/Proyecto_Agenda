package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val uid:String,
    val mail: String,
    val name:String
)