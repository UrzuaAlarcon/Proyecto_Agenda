package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.toError
import bootcamp.cl.proyecto_agenda.Presenters.NewMedPresenter
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewMedBinding

class NewMedFragment : Fragment() {

    lateinit var binding: FragmentNewMedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewMedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create an instance of the presenter for handling new medication
        val presenter = NewMedPresenter(this)

        // Initialize the AgendaDb database instance
        val agendaDb: AgendaDb? = AgendaDb.getDataBase(requireContext())

        // Retrieve the DAO (Data Access Object) for medications from the database
        val newMedDao = agendaDb?.medsDao()

        // Set a click listener for the "Add New Med" button
        binding.btnAddNewMed.setOnClickListener {
            // Check if any required field is empty
            if (binding.newMedName.text!!.isEmpty() ||
                binding.newMedIndication.text!!.isEmpty()
            ) {
                // Call the function to handle empty fields
                notEmptyFields()
                return@setOnClickListener
            } else {
                // Add the new medication to the database using the presenter
                presenter.addMedToDataBase(
                    newMedDao,
                    binding.newMedName.text.toString(),
                    binding.newMedIndication.text.toString(),
                    getUid()
                )
                // Clear the text boxes after adding the medication
                binding.newMedName.text?.clear()
                binding.newMedIndication.text?.clear()

                // Show an alert to indicate successful addition of medication
                presenter.showAlert()
            }
        }
    }

    // Function to handle empty fields and display an error state for each field
    private fun notEmptyFields() {
        if (binding.newMedName.text!!.isEmpty()) {
            binding.newMedName.toError()
        }
        if (binding.newMedIndication.text!!.isEmpty()) {
            binding.newMedIndication.toError()
        }
    }

}