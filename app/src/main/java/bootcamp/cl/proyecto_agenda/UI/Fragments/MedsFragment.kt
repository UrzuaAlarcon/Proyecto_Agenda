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
import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMeds
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
        agendaDb = AgendaDb.getDataBase(requireContext())
        val medDao = agendaDb.medsDao()


        recyclerMeds = binding.recycleMeds

        setRecyclerView(medDao)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddMeds.setOnClickListener {

            findNavController().navigate(R.id.action_medsFragment_to_newMedFragment)
        }
    }


    private fun setRecyclerView(medsDao: MedsDao) {

        recyclerMeds.setHasFixedSize(true)
        recyclerMeds.itemAnimator = DefaultItemAnimator()
        adapterMeds = (RecyclerMedsAdapter(mutableListOf(), object : RecyclerMeds {

            override fun onClick(meds: Meds, position: Int) {

                val medsDao = agendaDb.medsDao()

                deleteMedAlert(medsDao,meds)

            }
        }))

        runBlocking {

            val listaActual = medsDao.getAll()

            coroutineScope {

                adapterMeds.setMedsList(listaActual.toMutableList() ?: mutableListOf())
            }
        }

        adapterMeds.notifyDataSetChanged()
        recyclerMeds.adapter = adapterMeds

    }

    private fun deleteMedAlert(medsDao: MedsDao, meds: Meds) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Borrar")
        builder.setMessage(
            "¿Esta seguro que desea borrar el medicamento? esta accion no podra ser " +
                    "deshecha"
        )
        builder.setPositiveButton("Aceptar") {

                dialog, which ->

            runBlocking {

                medsDao?.deleteMeds(meds)

                adapterMeds.deleteMeds(meds)

            }
        }
        builder.setNegativeButton(getString(R.string.cancelar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

}
