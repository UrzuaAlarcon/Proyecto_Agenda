package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Adapters.RecyclerMedsAdapter
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMeds
import bootcamp.cl.proyecto_agenda.Models.Meds
import bootcamp.cl.proyecto_agenda.Provider.MedsProvider
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentMedsBinding


class MedsFragment : Fragment() {


    private lateinit var binding:FragmentMedsBinding
    private lateinit var recyclerMeds: RecyclerView
    private val layoutManager by lazy { LinearLayoutManager(context) }
    private val listOfMeds by lazy {getMedsFromProvider()}
    private lateinit var adapterMeds: RecyclerMedsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMedsBinding.inflate(inflater,container,false)


        recyclerMeds = binding.recycleMeds

        setRecyclerView()
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddMeds.setOnClickListener {

            findNavController().navigate(R.id.action_medsFragment_to_newMedFragment)
        }

    }

    private fun setRecyclerView(){

        recyclerMeds.setHasFixedSize(true)
        recyclerMeds.itemAnimator = DefaultItemAnimator()

       /* if (layoutManager == null){
            recyclerMeds.layoutManager = layoutManager
        }else{

            //hacer algo aqui para volver a la vista anterior
        }*/
        recyclerMeds.layoutManager = layoutManager
        adapterMeds = (RecyclerMedsAdapter(listOfMeds, object : RecyclerMeds {
            override fun onClick(meds: Meds, position: Int) {
                Toast.makeText(activity, "En implementacion", Toast.LENGTH_SHORT).show()
            }

         /*   override fun onDelete(flight: Flight, position: Int) {
                listofCitys.removeAt(position)
                adapterFlight.notifyItemChanged(position)
            }*/

        }))


        adapterMeds.notifyDataSetChanged()
        recyclerMeds.adapter = adapterMeds


    }

    private fun getMedsFromProvider():MutableList<Meds>{

        var listMeds = MedsProvider.listOfMeds

        return listMeds

    }




}