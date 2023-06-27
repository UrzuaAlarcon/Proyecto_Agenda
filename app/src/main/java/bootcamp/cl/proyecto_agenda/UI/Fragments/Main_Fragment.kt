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
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(inflater, container, false)
        agendaDB = AgendaDb.getDataBase(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val docDao = agendaDB.docDao()

        runBlocking{

            setNextAppointmentsView(docDao)

        }

        binding.btnMeds.setOnClickListener {

            findNavController().navigate(R.id.action_main_Fragment2_to_medsFragment)
        }

        binding.btnAppointments.setOnClickListener {

            findNavController().navigate(R.id.action_main_Fragment2_to_docAppointmentsFragment)
        }

        binding.btnTestAppointments.setOnClickListener {

            Toast.makeText(context, getString(R.string.en_implementacion), Toast.LENGTH_SHORT)
                .show()

        }

        binding.btnPrescriptions.setOnClickListener {

            Toast.makeText(context,getString(R.string.en_implementacion), Toast.LENGTH_SHORT)
                .show()

        }

        binding.btnLogOut.setOnClickListener {

            val preferences = context?.getSharedPreferences(
                getString
                    (R.string.preferences_file), Context.MODE_PRIVATE
            )?.edit()
            preferences?.clear()
            preferences?.apply()

            FirebaseAuth.getInstance().signOut()

            findNavController().navigate(R.id.action_main_Fragment2_to_home2)

        }


    }

    suspend fun setNextAppointmentsView(docAppointmentDao: DocAppointmentDao){

        val currentTimeStamp = System.currentTimeMillis()
        val listView = binding.listViewId
        val appointments = docAppointmentDao?.getNextAppointments(currentTimeStamp, getUid())?.toList() ?: emptyList()

        val arrayAdapter = NextAppointmentsAdapter(requireContext(), appointments)

        listView.adapter = arrayAdapter


    }

}



