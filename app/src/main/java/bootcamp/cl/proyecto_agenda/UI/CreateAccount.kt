package bootcamp.cl.proyecto_agenda.UI

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding
import bootcamp.cl.proyecto_agenda.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccount : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSaveCreateAccount.setOnClickListener {

            if (binding.userEmail.text!!.isNotEmpty() && binding.userPassword.text!!.isNotEmpty()){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.userEmail.text.
                toString(), binding.userPassword.text.toString())

                findNavController().navigate(R.id.action_createAccount2_to_main_Fragment2)

            }else{

                Toast.makeText(Activity(), "Algo ha salido mal", Toast.LENGTH_SHORT).show()
            }



        }

    }

    }



