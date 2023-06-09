package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Adapters.RecyclerMedsAdapter
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.MedsDao
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMeds
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Provider.MedsProvider
import bootcamp.cl.proyecto_agenda.Provider.MedsProvider.Companion.listOfMeds
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentMedsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MedsFragment : Fragment() {


    private lateinit var binding: FragmentMedsBinding
    private lateinit var recyclerMeds: RecyclerView
    private lateinit var adapterMeds: RecyclerMedsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMedsBinding.inflate(inflater, container, false)
        val agendaDb: AgendaDb = AgendaDb.getDataBase(requireContext())
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
            override fun onClick(meds: Meds, position: Int) {}
        }))


        runBlocking {

            val listaActual = medsDao.getAll()

            coroutineScope {

                adapterMeds.setMedsList(listaActual?.toMutableList() ?: mutableListOf())
            }
        }

        adapterMeds.notifyDataSetChanged()
        recyclerMeds.adapter = adapterMeds

    }

}
