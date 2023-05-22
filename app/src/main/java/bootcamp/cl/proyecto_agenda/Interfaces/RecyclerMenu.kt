package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.Models.Option

interface RecyclerMenu {

    fun onClick(opction:Option, position: Int)
    fun onDelete(opction:Option, position: Int)


}