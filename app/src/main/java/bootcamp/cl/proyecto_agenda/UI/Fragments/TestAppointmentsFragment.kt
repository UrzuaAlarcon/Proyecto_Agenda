package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Adapters.RecyclerTestAdapter
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.TestAppointmentDao
import bootcamp.cl.proyecto_agenda.Interfaces.Recyclers.RecyclerTestAppointments
import bootcamp.cl.proyecto_agenda.Models.TestAppointment
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentTestAppointmentsBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

class TestAppointmentsFragment : Fragment() {
    lateinit var binding:FragmentTestAppointmentsBinding
    lateinit var recyclerTest: RecyclerView
    lateinit var adapterTest: RecyclerTestAdapter
    lateinit var agendaDB: AgendaDb

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding =  FragmentTestAppointmentsBinding.inflate(inflater, container, false)
        agendaDB = AgendaDb.getDataBase(requireContext())
        val testDao = agendaDB.testDao()

        recyclerTest = binding.recycleTestAppointments

        setRecyclerView(testDao)

        // Inflate the layout for this fragment
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddTestAppointment.setOnClickListener {

            findNavController().navigate(R.id.action_testAppointmentsFragment_to_newTestAppointmentFragment)

        }

    }
    private fun setRecyclerView(testDao: TestAppointmentDao) {

        recyclerTest.itemAnimator = DefaultItemAnimator()
        adapterTest = RecyclerTestAdapter(mutableListOf(), object: RecyclerTestAppointments {
            override fun onClick(testAppointment: TestAppointment, position: Int) {
                val testDao = agendaDB.testDao()
                deleteTestAlert(testDao, testAppointment)
            }
        } )

        runBlocking {

            val listaActual = testDao.getAll(getUid())

            adapterTest.setTestAppointmentsList(listaActual.toMutableList())
        }

        adapterTest.notifyDataSetChanged()

        recyclerTest.adapter = adapterTest

    }
    private fun deleteTestAlert(testDao: TestAppointmentDao, testAppointment: TestAppointment){

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Borrar cita")
        builder.setMessage("¿Está seguro que desea borrar la cita? Esta acción no podrá ser deshecha.")
        builder.setPositiveButton(getString(R.string.aceptar)) { dialog, which ->
            runBlocking {
                testDao?.deleteTestAppointment(testAppointment)
                adapterTest.deleteTestAppointments(testAppointment)
            }
        }
        builder.setNegativeButton(getString(R.string.cancelar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

}