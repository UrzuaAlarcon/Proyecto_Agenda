package bootcamp.cl.proyecto_agenda.Models

import java.time.LocalDateTime


/**
 * Auxiliary class to receive data and show it in a list
 */
data class NextAppointmentData (
        val specialty: String,
        val dateAndTime: LocalDateTime,
        val location: String
        )