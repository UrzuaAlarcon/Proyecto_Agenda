package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.toError
import bootcamp.cl.proyecto_agenda.Presenters.NewTestAppointmentPresenterImpl
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewTestAppointmentBinding


class NewTestAppointmentFragment : Fragment() {

    lateinit var binding: FragmentNewTestAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewTestAppointmentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = NewTestAppointmentPresenterImpl(this)

        val agendaDB: AgendaDb? = AgendaDb.getDataBase(requireContext())

        val newTestDao = agendaDB?.testDao()

        binding.newTime.setOnClickListener {
            presenter.showTimePickerDialog()
        }

        binding.newDate.setOnClickListener {

            presenter.showDatePickerDialog()
        }

        binding.btnAddNewTestAppointment.setOnClickListener {

            // Check if any required field is empty
            if (binding.newTestType.text!!.isEmpty() ||
                binding.newDate.text!!.isEmpty() ||
                binding.newTime.text!!.isEmpty() ||
                binding.newLocation.text!!.isEmpty() ||
                binding.newIndication.text!!.isEmpty()
            ) {
                // Call the function to handle empty fields
                notEmptyFields()
                return@setOnClickListener
            } else {
                // Add the new doctor appointment to the database using the presenter
                presenter.addTestAppointmentToDataBase(
                    newTestDao!!,
                    binding.newTestType.text.toString(),
                    presenter.saveDateAndTimeSelected(),
                    binding.newLocation.text.toString(),
                    binding.newIndication.text.toString(),
                    getUid()
                )
                // Clear the text boxes after adding the appointment
                clearTextBoxes()
            }
        }
    }

    private fun clearTextBoxes() {
        binding.newTestType.text?.clear()
        binding.newDate.text?.clear()
        binding.newTime.text?.clear()
        binding.newLocation.text?.clear()
        binding.newIndication.text?.clear()
    }

    private fun notEmptyFields() {
        if (binding.newTestType.text!!.isEmpty()) {
            binding.newTestType.toError()
        }
        if (binding.newDate.text!!.isEmpty()) {
            binding.newDate.toError()
        }
        if (binding.newTime.text!!.isEmpty()) {
            binding.newTime.toError()
        }
        if (binding.newLocation.text!!.isEmpty()) {
            binding.newLocation.toError()
        }
        if (binding.newIndication.text!!.isEmpty()) {
            binding.newIndication.toError()
        }

    }

}