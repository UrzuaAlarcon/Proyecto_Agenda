package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.toError
import bootcamp.cl.proyecto_agenda.Presenters.NewDocAppointmentsPresenterImpls
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewDocAppoitmentBinding

class NewDocAppoitmentFragment : Fragment() {

    lateinit var binding: FragmentNewDocAppoitmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewDocAppoitmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create an instance of the presenter for handling new doctor appointments
        val presenter = NewDocAppointmentsPresenterImpls(this)

        // Initialize the AgendaDb database instance
        val agendaDb: AgendaDb? = AgendaDb.getDataBase(requireContext())

        // Retrieve the DAO (Data Access Object) for doctor appointments from the database
        val newDocDao = agendaDb?.docDao()

        // Set a click listener for the "New Date" button
        binding.newDate.setOnClickListener {
            // Show the date picker dialog using the presenter
            presenter.showDatePickerDialog()
        }

        // Set a click listener for the "New Time" button
        binding.newTime.setOnClickListener {
            // Show the time picker dialog using the presenter
            presenter.showTimePickerDialog()
        }

        // Set a click listener for the "Add New Doc Appointment" button
        binding.btnAddNewDocAppointment.setOnClickListener {
            // Check if any required field is empty
            if (binding.newDocName.text!!.isEmpty() ||
                binding.newSpecialty.text!!.isEmpty() ||
                binding.newDate.text!!.isEmpty() ||
                binding.newTime.text!!.isEmpty() ||
                binding.newLocation.text!!.isEmpty()
            ) {
                // Call the function to handle empty fields
                notEmptyFields()
                return@setOnClickListener
            } else {
                // Add the new doctor appointment to the database using the presenter
                presenter.addDocAppointmentToDataBase(
                    newDocDao!!,
                    binding.newDocName.text.toString(),
                    binding.newSpecialty.text.toString(),
                    presenter.saveDateAndTimeSelected(),
                    binding.newLocation.text.toString(),
                    getUid()
                )
                // Clear the text boxes after adding the appointment
                clearTextBoxes()
            }
        }
    }

    // Function to handle empty fields and display an error state for each field
    private fun notEmptyFields() {
        if (binding.newDocName.text!!.isEmpty()) {
            binding.newDocName.toError()
        }
        if (binding.newSpecialty.text!!.isEmpty()) {
            binding.newSpecialty.toError()
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
    }

    // Function to clear all text boxes
    private fun clearTextBoxes() {
        binding.newDocName.text?.clear()
        binding.newSpecialty.text?.clear()
        binding.newDate.text?.clear()
        binding.newTime.text?.clear()
        binding.newLocation.text?.clear()
    }
}