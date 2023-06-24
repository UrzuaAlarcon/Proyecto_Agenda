package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.DocAppointment

@Dao
interface DocAppointmentDao {
    /**
     * Retrieves all DocAppointments from the table.
     */
    @Query("SELECT * FROM DocAppointment")
    suspend fun getAll(): MutableList<DocAppointment>

    /**
     * Inserts a new DocAppointment into the table.
     */
    @Insert
    suspend fun insertDocAppointment(docAppointment: DocAppointment)

    /**
     * Deletes a specific DocAppointment from the table.
     */
    @Delete
    suspend fun deleteDocAppointment(docAppointment: DocAppointment)

    /**
     * Updates a specific DocAppointment in the table.
     */
    @Update
    suspend fun updateDocApoointment(docAppointment: DocAppointment)
}