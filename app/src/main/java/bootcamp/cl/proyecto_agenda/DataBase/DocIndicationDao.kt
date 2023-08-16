package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.Models.DocIndications

@Dao
interface DocIndicationDao {

    /**
     * Retrieves all DocIndications from the table.
     */
    @Query("SELECT * FROM DocIndications WHERE userId =:uid")
    suspend fun getAll(uid:String):MutableList<DocIndications>

    /**
     * Inserts a new DocIndications into the table.
     */
    @Insert
    suspend fun insertDocIndications(docIndications: DocIndications)

    /**
     * Deletes a specific DocIndications from the table.
     */
    @Delete
    suspend fun deleteDocIndications(docIndications: DocIndications)

    /**
     * Updates a specific DocIndications in the table.
     */
    @Update
    suspend fun updateDocIndications(docIndications: DocIndications)


}