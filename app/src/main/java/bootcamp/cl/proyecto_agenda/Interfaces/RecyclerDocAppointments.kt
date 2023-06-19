package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import java.text.FieldPosition

interface RecyclerDocAppointments {

    fun onClick (docAppointment: DocAppointment, position: Int)

}