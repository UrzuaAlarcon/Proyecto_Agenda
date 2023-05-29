package bootcamp.cl.proyecto_agenda.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Models.User

@Database(
    entities = [User::class, Meds::class],
    version = 1
)

abstract class AgendaDb: RoomDatabase(){

    abstract fun userDao():UserDao

    abstract fun medsDao(): MedsDao

}