package bootcamp.cl.proyecto_agenda.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Models.User

@Database(
    entities = [Meds::class , DocAppointment::class],
    version = 1
)

abstract class AgendaDb: RoomDatabase(){


    abstract fun medsDao(): MedsDao

    abstract fun docDao():DocAppointmentDao

    companion object{

        @Volatile
        private var INSTANCE:AgendaDb? = null

        fun getDataBase(context: Context): AgendaDb{

            val instanceDataBase = INSTANCE
            if (instanceDataBase != null){

                return instanceDataBase
            }
            synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext,
                    AgendaDb::class.java,
                    "AgendaDataBase").build()

                INSTANCE = instance

            }

            return INSTANCE!!

        }

    }

}