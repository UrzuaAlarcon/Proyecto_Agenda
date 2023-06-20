package bootcamp.cl.proyecto_agenda.Presenters

import android.app.AlertDialog
import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Interfaces.AddNewMedPresenter
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.UI.Fragments.NewMedFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NewMedPresenter(private val newMed: NewMedFragment) : AddNewMedPresenter {


    override fun addMedToDataBase(
        medsDao: MedsDao?,
        name: String,
        indication: String
    ) {

        var med = Meds(medsName = name, medsIndication = indication)

        runBlocking {
            launch(Dispatchers.IO) {
                medsDao?.insertMeds(med)
            }
        }
    }

    override fun showAlert() {

        val builder = AlertDialog.Builder(newMed.context)
        builder.setTitle(newMed.context?.getString(R.string.guardado_exitoso))
        builder.setMessage("Medicamento guardado exitosamente")
        builder.setPositiveButton(newMed.context?.getString(R.string.aceptar), null)

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    override suspend fun getAllMeds(medsDao: MedsDao?): List<Meds> {
        return medsDao?.getAll()?.toList() ?: emptyList()
    }
}