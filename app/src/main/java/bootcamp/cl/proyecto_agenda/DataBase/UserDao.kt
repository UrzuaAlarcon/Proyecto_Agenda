package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import bootcamp.cl.proyecto_agenda.Models.User

@Dao
interface UserDao {

    /**
     * Retrieves all User from the table.
     */
    @Query("SELECT * FROM User")
    suspend fun getAll(): MutableList<User>

    /**
     * Inserts a new User into the table.
     */
    @Insert
    suspend fun insertUser(user: User)

}