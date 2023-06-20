package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.Presenters.NewDocAppointmentsPresenterImpls
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewDocAppoitmentBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewDocAppoitmentFragment : Fragment() {

    lateinit var binding: FragmentNewDocAppoitmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewDocAppoitmentBinding.inflate(layoutInflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = NewDocAppointmentsPresenterImpls(this)
        val agendaDb: AgendaDb? = AgendaDb.getDataBase(requireContext())
        val newDocDao = agendaDb?.docDao()

        binding.newDate.setOnClickListener {

            presenter.showDatePickerDialog()

        }

        binding.newTime.setOnClickListener {

            presenter.showTimePickerDialog()

        }

        binding.btnAddNewDocAppointment.setOnClickListener {

            presenter.addDocAppointmentToDataBase(
                newDocDao!!,
                binding.newDocName.toString(),
                binding.newSpecialty.toString(),
                presenter.saveDateAndTimeSelected(),
                binding.newLocation.toString()
            )

            clearTextBoxes()

        }
    }

    private fun clearTextBoxes(){

        binding.newDocName.text?.clear()
        binding.newSpecialty.text?.clear()
        binding.newDate.text?.clear()
        binding.newTime.text?.clear()
        binding.newLocation.text?.clear()

    }


}