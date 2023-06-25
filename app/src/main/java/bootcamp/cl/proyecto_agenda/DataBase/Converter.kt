package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

class Converter {
    /**
     * Converts a LocalDateTime object to a timeStamp(Long).
     */
    @TypeConverter
    fun fromLocalDateTime(dateAndTime: LocalDateTime?): Long? {

// Convierte el LocalDateTime a ZonedDateTime utilizando la zona horaria predeterminada del dispositivo
        val zonedDateTime = dateAndTime?.atZone(ZoneId.systemDefault())

// Obtén el timestamp en milisegundos
        val timestamp = zonedDateTime?.toInstant()?.toEpochMilli()

        return timestamp
    }

    /**
     * Converts a Long(timeStamp) to a LocalDateTime object.
     */
    @TypeConverter
    fun toLocalDateTime(dateAndTime: Long?): LocalDateTime? {

        val instant = dateAndTime?.let { Instant.ofEpochMilli(it) }

// Convierte el Instant a ZonedDateTime utilizando la zona horaria predeterminada del dispositivo
        val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault())

// Obtén el LocalDateTime a partir de ZonedDateTime

        return zonedDateTime.toLocalDateTime()

    }
}