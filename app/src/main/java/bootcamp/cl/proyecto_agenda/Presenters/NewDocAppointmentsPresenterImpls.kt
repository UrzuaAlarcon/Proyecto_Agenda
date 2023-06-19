package bootcamp.cl.proyecto_agenda.Presenters

import bootcamp.cl.proyecto_agenda.Interfaces.newDocAppointmentPresenter
import bootcamp.cl.proyecto_agenda.UI.Fragments.TimePickerFragment
import java.time.LocalDateTime

class NewDocAppointmentsPresenterImpls(val newDocAppointmentPresenter: newDocAppointmentPresenter)
    :newDocAppointmentPresenter {

    override fun onTimeSelected(time: String) {
        TODO("Not yet implemented")
    }

    override fun onDateSelected(day: Int, month: Int, year: Int) {
        TODO("Not yet implemented")
    }

    override fun saveDateAndTimeSelected(): LocalDateTime {
        TODO("Not yet implemented")
    }

    override fun formatDate(dateSelected: String): String {
        TODO("Not yet implemented")
    }


}