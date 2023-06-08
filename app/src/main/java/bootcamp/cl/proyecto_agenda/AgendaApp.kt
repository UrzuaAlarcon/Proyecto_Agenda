package bootcamp.cl.proyecto_agenda

import android.app.Application
import androidx.room.Room
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb

class AgendaApp: Application () {

    val room = Room.databaseBuilder(this, AgendaDb::class.java,"AgendaDataBase").build()

}