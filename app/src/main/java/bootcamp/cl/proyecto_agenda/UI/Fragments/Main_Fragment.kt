package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bootcamp.cl.proyecto_agenda.Adapters.RecyclerOptionAdapter
import bootcamp.cl.proyecto_agenda.Interfaces.RecyclerMenu
import bootcamp.cl.proyecto_agenda.Models.Option
import bootcamp.cl.proyecto_agenda.Provider.OptionProvider
import bootcamp.cl.proyecto_agenda.databinding.FragmentMainBinding

class Main_Fragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerOptionsMenu: RecyclerView
    private val layoutManager by lazy { GridLayoutManager(context,2,RecyclerView.VERTICAL,true) }
    private val listofOptions by lazy { getOptionsFromProvider() }
    private lateinit var adapterOptions: RecyclerOptionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(inflater, container, false)

        recyclerOptionsMenu = binding.recyclerMain

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {

        recyclerOptionsMenu.setHasFixedSize(true)
        recyclerOptionsMenu.itemAnimator = DefaultItemAnimator()
        recyclerOptionsMenu.layoutManager = layoutManager
        adapterOptions = (RecyclerOptionAdapter(listofOptions, object : RecyclerMenu {


            override fun onClick(opction: Option, position: Int) {
                Toast.makeText(context, "En implementacion", Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(opction: Option, position: Int) {
                Toast.makeText(context, "En implementacion", Toast.LENGTH_SHORT).show()
            }

        }))

        adapterOptions.notifyDataSetChanged()
        recyclerOptionsMenu.adapter = adapterOptions


    }
}

private fun getOptionsFromProvider(): MutableList<Option> {

    var listOptions = OptionProvider.listOfOptions

    return listOptions
}


