package bootcamp.cl.proyecto_agenda.Models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity("Users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    var name:String,
    var mail:String,
    var age:Int
)
