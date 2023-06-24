package bootcamp.cl.proyecto_agenda.Interfaces

import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Models.Meds

interface AddNewMedPresenter {
    /**
     * Adds a new medication to the database using the provided MedsDao.
     * Takes the name and indication of the medication as parameters.
     */
    fun addMedToDataBase(
        medsDao: MedsDao?,
        name: String,
        indication: String
    )

    /**
     * Shows an alert to the user.
     */
    fun showAlert()

    /**
     * Retrieves all medications from the database using the provided MedsDao.
     * Returns a list of Meds.
     */
    suspend fun getAllMeds(medsDao: MedsDao?): List<Meds>
}