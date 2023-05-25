package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Models.Option

interface RecyclerMeds {

    fun onClick(meds: Meds, position: Int)
    //fun onDelete(meds: Meds, position: Int)


}