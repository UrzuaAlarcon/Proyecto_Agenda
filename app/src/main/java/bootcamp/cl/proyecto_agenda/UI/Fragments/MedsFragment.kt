package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Adapters.RecyclerMedsAdapter
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Interfaces.Recyclers.RecyclerMeds
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentMedsBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking


class MedsFragment : Fragment() {

    private lateinit var binding: FragmentMedsBinding
    private lateinit var recyclerMeds: RecyclerView
    private lateinit var adapterMeds: RecyclerMedsAdapter
    private lateinit var agendaDb: AgendaDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMedsBinding.inflate(inflater, container, false)

        // Initialize the AgendaDb database instance
        agendaDb = AgendaDb.getDataBase(requireContext())

        // Retrieve the DAO (Data Access Object) for the medications from the database
        val medDao = agendaDb.medsDao()

        recyclerMeds = binding.recycleMeds

        // Set up the RecyclerView for displaying medications
        setRecyclerView(medDao)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set a click listener for the "Add Meds" button
        binding.btnAddMeds.setOnClickListener {
            // Navigate to the NewMedFragment using the NavController
            findNavController().navigate(R.id.action_medsFragment_to_newMedFragment)
        }
    }

    // Function to set up the RecyclerView for displaying medications
    private fun setRecyclerView(medsDao: MedsDao) {
        recyclerMeds.setHasFixedSize(true)
        recyclerMeds.itemAnimator = DefaultItemAnimator()

        // Create an instance of the RecyclerMedsAdapter and set it as the adapter for the RecyclerView
        adapterMeds = RecyclerMedsAdapter(mutableListOf(), object : RecyclerMeds {
            override fun onClick(meds: Meds, position: Int) {
                val medsDao = agendaDb.medsDao()

                // Show a confirmation dialog to delete the medication
                deleteMedAlert(medsDao, meds)
            }
        })

        runBlocking {
            // Retrieve the current list of medications for the current user ID
            val listaActual = medsDao.getAll(getUid())

            coroutineScope {
                // Set the retrieved medications list to the adapter
                adapterMeds.setMedsList(listaActual.toMutableList() ?: mutableListOf())
            }
        }

        adapterMeds.notifyDataSetChanged()
        recyclerMeds.adapter = adapterMeds
    }

    // Function to show a confirmation dialog for deleting a medication
    private fun deleteMedAlert(medsDao: MedsDao, meds: Meds) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Borrar")
        builder.setMessage("¿Está seguro que desea borrar el medicamento? Esta acción no podrá ser deshecha.")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            runBlocking {
                // Delete the medication from the database and update the adapter
                medsDao?.deleteMeds(meds)
                adapterMeds.deleteMeds(meds)
            }
        }
        builder.setNegativeButton(getString(R.string.cancelar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
