package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.Adapters.NextAppointmentsAdapter
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.DocAppointmentDao
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking

class Main_Fragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    lateinit var agendaDB: AgendaDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflates the layout for the fragment using the FragmentMainBinding class
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // Initializes the agendaDB variable with the AgendaDb database instance
        agendaDB = AgendaDb.getDataBase(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieves the DAO (Data Access Object) for the documents from the database
        val docDao = agendaDB.docDao()

        runBlocking{
            // Calls the function to set up the view for the next appointments asynchronously
            setNextAppointmentsView(docDao)
        }

        // Sets a click listener for the "Meds" button
        binding.btnMeds.setOnClickListener {
            // Navigates to the MedsFragment using the NavController
            findNavController().navigate(R.id.action_main_Fragment2_to_medsFragment)
        }

        // Sets a click listener for the "Appointments" button
        binding.btnAppointments.setOnClickListener {
            // Navigates to the DocAppointmentsFragment using the NavController
            findNavController().navigate(R.id.action_main_Fragment2_to_docAppointmentsFragment)
        }

        // Sets a click listener for the "Test Appointments" button
        binding.btnTestAppointments.setOnClickListener {
            // Displays a toast message indicating that this feature is under implementation
            Toast.makeText(context, getString(R.string.en_implementacion), Toast.LENGTH_SHORT).show()
        }

        // Sets a click listener for the "Prescriptions" button
        binding.btnPrescriptions.setOnClickListener {
            // Displays a toast message indicating that this feature is under implementation
            Toast.makeText(context, getString(R.string.en_implementacion), Toast.LENGTH_SHORT).show()
        }

        // Sets a click listener for the "Log Out" button
        binding.btnLogOut.setOnClickListener {
            // Clears the shared preferences file
            val preferences = context?.getSharedPreferences(getString(R.string.preferences_file), Context.MODE_PRIVATE)?.edit()
            preferences?.clear()
            preferences?.apply()

            // Signs out the user from Firebase Authentication
            FirebaseAuth.getInstance().signOut()

            // Navigates to the Home2 fragment using the NavController
            findNavController().navigate(R.id.action_main_Fragment2_to_home2)
        }
    }

    // Function to set up the view for the next appointments
    suspend fun setNextAppointmentsView(docAppointmentDao: DocAppointmentDao) {
        // Gets the current timestamp
        val currentTimeStamp = System.currentTimeMillis()

        // Retrieves the ListView from the layout
        val listView = binding.listViewId

        // Retrieves the next appointments from the database for the current user ID
        val appointments = docAppointmentDao?.getNextAppointments(currentTimeStamp, getUid())?.toList() ?: emptyList()

        // Creates an instance of the NextAppointmentsAdapter using the retrieved appointments
        val arrayAdapter = NextAppointmentsAdapter(requireContext(), appointments)

        // Sets the adapter for the ListView
        listView.adapter = arrayAdapter
    }
}



