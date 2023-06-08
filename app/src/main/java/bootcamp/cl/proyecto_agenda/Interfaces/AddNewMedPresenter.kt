package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Models.Meds

interface AddNewMedPresenter {

    fun addMedToDataBase(medsDao: MedsDao?,
                                 name:String,
                                 indication:String)

    fun showAlert()

    suspend fun getAllMeds(medsDao: MedsDao?):List<Meds>
}