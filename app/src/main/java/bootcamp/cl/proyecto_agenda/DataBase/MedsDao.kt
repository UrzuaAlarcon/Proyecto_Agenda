package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Models.User

@Dao

interface MedsDao {

    @Query("SELECT *  FROM Meds")
    suspend fun getAll():MutableList<Meds>

    @Query("SELECT * FROM Meds WHERE id = :id")
    suspend fun getAllById(id:Int): MutableList<Meds>

    @Query("SELECT medsName FROM Meds WHERE id = :id ")
    suspend fun getMedsNameById(id: Int): MutableList<Meds>

    @Insert
    suspend fun insertMeds(meds: Meds)

    @Delete
    suspend fun deleteMeds(meds: Meds)

    @Update
    suspend fun updateMeds(meds: Meds)

}