package bootcamp.cl.proyecto_agenda.Models

import java.time.LocalDateTime

data class NextAppointmentData (
        val specialty: String,
        val dateAndTime: LocalDateTime,
        val location: String
        )