package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.TestAppointment

@Dao
interface TestAppointmentDao {

    /**
     * Retrieves all DocAppointments from the table.
     */
    @Query("SELECT * FROM TestAppointment WHERE userId =:uid ")
    suspend fun getAll(uid:String): MutableList<TestAppointment>

    /**
     * Inserts a new TestAppointment into the table.
     */
    @Insert
    suspend fun inserTestAppointment(testAppointment: TestAppointment)

    /**
     * Deletes a specific TestAppointment from the table.
     */
    @Delete
    suspend fun deleteTestAppointment(testAppointment: TestAppointment)

    /**
     * Updates a specific TestAppointment in the table.
     */
    @Update
    suspend fun updateTestApoointment(testAppointment: TestAppointment)

}