package bootcamp.cl.proyecto_agenda.Interfaces.Recyclers

import bootcamp.cl.proyecto_agenda.Models.TestAppointment

interface RecyclerTestAppointments {

    // This interface defines an onClick method that will be used to handle the
    // click event on a TestAppointment item in the RecyclerView.

    fun onClick(testAppointment:TestAppointment, position:Int)

}