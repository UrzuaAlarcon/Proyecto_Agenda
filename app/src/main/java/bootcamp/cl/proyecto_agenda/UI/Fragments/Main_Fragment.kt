package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth

class Main_Fragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMeds.setOnClickListener {

            findNavController().navigate(R.id.action_main_Fragment2_to_medsFragment)
        }

        binding.btnLogOut.setOnClickListener {

            val preferences = context?.getSharedPreferences(getString
                (R.string.preferences_file), Context.MODE_PRIVATE)?.edit()
            preferences?.clear()
            preferences?.apply()

            FirebaseAuth.getInstance().signOut()

            findNavController().navigate(R.id.action_main_Fragment2_to_home2)

        }


    }

}


