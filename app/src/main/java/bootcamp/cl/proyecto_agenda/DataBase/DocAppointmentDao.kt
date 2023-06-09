package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.Models.NextAppointmentData
import bootcamp.cl.proyecto_agenda.Models.User

@Dao
interface DocAppointmentDao {

    /**
     * Retrieves all DocAppointments from the table.
     */
    @Query("SELECT * FROM DocAppointment WHERE userId =:uid ")
    suspend fun getAll(uid:String): MutableList<DocAppointment>

    @Query("SELECT specialty, dateAndTime, location FROM DocAppointment " +
            "WHERE dateAndTime >= :currentDate AND userId = :uid ORDER BY dateAndTime ASC LIMIT 2" )
    suspend fun getNextAppointments(currentDate : Long, uid:String):List<NextAppointmentData>

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