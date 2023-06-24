package bootcamp.cl.proyecto_agenda.DataBase



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.Models.Meds

@Database(
    entities = [Meds::class, DocAppointment::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AgendaDb : RoomDatabase() {

    /**
     * Defines the DAO for accessing the Meds table.
     */
    abstract fun medsDao(): MedsDao

    /**
     * Defines the DAO for accessing the DocAppointment table.
     */
    abstract fun docDao(): DocAppointmentDao

    companion object {

        @Volatile
        private var INSTANCE: AgendaDb? = null

        /**
         * Retrieves the singleton instance of the AgendaDb database.
         */
        fun getDataBase(context: Context): AgendaDb {
            val instanceDataBase = INSTANCE
            if (instanceDataBase != null) {
                return instanceDataBase
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AgendaDb::class.java,
                    "AgendaDataBase"
                ).build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }
    }
}