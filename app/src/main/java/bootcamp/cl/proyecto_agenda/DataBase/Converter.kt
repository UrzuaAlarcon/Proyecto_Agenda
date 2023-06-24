package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.TypeConverter
import java.time.LocalDateTime

class Converter {
    /**
     * Converts a LocalDateTime object to a String.
     */
    @TypeConverter
    fun fromLocalDateTime(dateAndTime: LocalDateTime?): String? {
        return dateAndTime?.toString()
    }

    /**
     * Converts a String to a LocalDateTime object.
     */
    @TypeConverter
    fun toLocalDateTime(dateAndTime: String?): LocalDateTime? {
        return dateAndTime?.let { LocalDateTime.parse(it) }
    }
}