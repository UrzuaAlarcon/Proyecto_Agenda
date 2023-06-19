package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.DocAppointment

@Dao
interface DocAppointmentDao {

    @Query ("SELECT * FROM DocAppointment")
    suspend fun getAll():MutableList<DocAppointment>

    @Insert
    suspend fun insertDocAppointment(docAppointment: DocAppointment)

    @Delete
    suspend fun deleteDocAppointment(docAppointment: DocAppointment)

    @Update
    suspend fun updateDocApoointment(docAppointment: DocAppointment)

    }

