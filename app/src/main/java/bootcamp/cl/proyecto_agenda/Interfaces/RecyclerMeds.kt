package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.Models.Meds

interface RecyclerMeds {

    // This interface defines an onClick method that will be used to handle the
    // click event on a Med item in the RecyclerView.

    fun onClick(meds: Meds, position: Int)


}