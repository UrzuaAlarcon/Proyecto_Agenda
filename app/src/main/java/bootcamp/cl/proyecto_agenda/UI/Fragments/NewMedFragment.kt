package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.Presenters.NewMedPresenter
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewMedBinding
import kotlinx.coroutines.runBlocking

class NewMedFragment : Fragment() {

    lateinit var binding: FragmentNewMedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewMedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = NewMedPresenter(this)
        val agendaDb: AgendaDb? = AgendaDb.getDataBase(requireContext())
        val newMedDao = agendaDb?.medsDao()

        binding.btnAddNewMed.setOnClickListener {

            presenter.addMedToDataBase(
                newMedDao,
                binding.newMedName.text.toString(),
                binding.newMedIndication.text.toString()
            )
            runBlocking {
                Log.i("pruebas", "onViewCreated: ${presenter.getAllMeds(newMedDao)}")
            }

        }



    }

}