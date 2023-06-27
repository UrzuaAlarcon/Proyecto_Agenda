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
import bootcamp.cl.proyecto_agenda.Adapters.RecyclerDocsAdapter
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.DocAppointmentDao
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerDocAppointments
import bootcamp.cl.proyecto_agenda.Models.DocAppointment
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentDocAppointmentsBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class DocAppointmentsFragment : Fragment() {

    lateinit var binding: FragmentDocAppointmentsBinding
    lateinit var recyclerDoc: RecyclerView
    lateinit var adapterDoc: RecyclerDocsAdapter
    lateinit var agendaDB: AgendaDb

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDocAppointmentsBinding.inflate(inflater, container, false)
        agendaDB = AgendaDb.getDataBase(requireContext())
        val docDao = agendaDB.docDao()

        recyclerDoc = binding.recycleDocAppointments

        setRecyclerView(docDao)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate to the newDocAppoitmentFragment when the "btnAddDocAppointment" button is clicked
        binding.btnAddDocAppointment.setOnClickListener {
            findNavController().navigate(R.id.action_docAppointmentsFragment_to_newDocAppoitmentFragment)
        }
    }

    private fun setRecyclerView(docDao: DocAppointmentDao) {
        // Set item animator and adapter for the RecyclerView
        recyclerDoc.itemAnimator = DefaultItemAnimator()
        adapterDoc = RecyclerDocsAdapter(mutableListOf(), object : RecyclerDocAppointments {
            override fun onClick(docAppointment: DocAppointment, position: Int) {
                val docDao = agendaDB.docDao()
                deleteDocAlert(docDao, docAppointment)
            }
        })

        runBlocking {
            val listaActual = docDao.getAll(getUid())

            coroutineScope {
                adapterDoc.setDocAppointmentsList(listaActual.toMutableList())
            }
        }

        adapterDoc.notifyDataSetChanged()
        recyclerDoc.adapter = adapterDoc
    }

    private fun deleteDocAlert(docDao: DocAppointmentDao, docAppointment: DocAppointment) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Borrar cita")
        builder.setMessage("¿Está seguro que desea borrar el medicamento? Esta acción no podrá ser deshecha.")
        builder.setPositiveButton(getString(R.string.aceptar)) { dialog, which ->
            runBlocking {
                docDao?.deleteDocAppointment(docAppointment)
                adapterDoc.deleteDocAppointments(docAppointment)
            }
        }
        builder.setNegativeButton(getString(R.string.cancelar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}