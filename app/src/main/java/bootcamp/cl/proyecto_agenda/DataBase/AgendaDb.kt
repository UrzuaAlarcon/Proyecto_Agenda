package bootcamp.cl.proyecto_agenda.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    /*companion object{

        @Volatile
        private var INSTANCE:AgendaDb? = null

        fun getDataBase(context: Context): AgendaDb?{

            val instanceDataBase = INSTANCE
            if (instanceDataBase != null){

                return instanceDataBase
            }
            synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext,
                    AgendaDb::class.java,
                    "agendaDataBAse").build()

                INSTANCE = instance

            }

            return INSTANCE

        }

    }*/

}