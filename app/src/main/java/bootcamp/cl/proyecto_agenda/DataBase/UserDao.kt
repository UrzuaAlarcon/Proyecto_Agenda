package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bootcamp.cl.proyecto_agenda.Models.User


@Dao
interface UserDao {

    @Query("SELECT *  FROM Users")
    suspend fun getAll(): MutableList<User>

    @Query("SELECT * FROM Users WHERE id = :id")
    suspend fun getAllById(id: Int): User

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}