package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.Meds

@Dao
interface MedsDao {
    /**
     * Retrieves all Meds from the table.
     */
    @Query("SELECT * FROM MEDS WHERE userId =:uid ")
    suspend fun getAll(uid:String): MutableList<Meds>

    /**
     * Retrieves Meds with a specific id from the table.
     */
    @Query("SELECT * FROM Meds WHERE id = :id")
    suspend fun getAllById(id: Int): MutableList<Meds>

    /**
     * Inserts a new Meds into the table.
     */
    @Insert
    suspend fun insertMeds(meds: Meds)

    /**
     * Deletes a specific Meds from the table.
     */
    @Delete
    suspend fun deleteMeds(meds: Meds)

    /**
     * Updates a specific Meds in the table.
     */
    @Update
    suspend fun updateMeds(meds: Meds)
}