package bootcamp.cl.proyecto_agenda.Presenters

import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Interfaces.AddNewMedPresenter
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.UI.Fragments.NewMedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NewMedPresenter(private val newMed:NewMedFragment):AddNewMedPresenter {


    override fun addMedToDataBase(medsDao: MedsDao?,
                                          name:String,
                                          indication:String) {

        var med = Meds(medsName = name, medsIndication = indication)

        runBlocking {
            launch(Dispatchers.IO) {
                medsDao?.insertMeds (med)
            }
        }



    }

    override fun showAlert() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllMeds(medsDao: MedsDao?): List<Meds> {
       return  medsDao?.getAll()?.toList()  ?: emptyList()
    }


}