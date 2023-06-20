package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converter {

    @TypeConverter
    fun fromLocalDateTime(dateAndTime: LocalDateTime?): String? {
        return dateAndTime?.toString()
    }

    @TypeConverter
    fun toLocalDateTime(dateAndTime: String?): LocalDateTime? {
        return dateAndTime?.let { LocalDateTime.parse(it) }
    }

}