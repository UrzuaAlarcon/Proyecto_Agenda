package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentDocIndicationsBinding


class DocIndicationsFragment : Fragment() {

    lateinit var binding: FragmentDocIndicationsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDocIndicationsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddDocIndication.setOnClickListener {

            findNavController().navigate(R.id.action_docIndicationsFragment_to_newDocIndicationFragment)
        }

    }



}