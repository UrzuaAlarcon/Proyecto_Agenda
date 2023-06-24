package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import java.text.FieldPosition

interface RecyclerDocAppointments {

    // This interface defines an onClick method that will be used to handle the
    // click event on a DocAppointment item in the RecyclerView.

    fun onClick (docAppointment: DocAppointment, position: Int)

}