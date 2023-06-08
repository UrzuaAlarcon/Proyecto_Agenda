package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.Presenters.CreateAccountPresenter
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding


class CreateAccount : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateAccountBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = CreateAccountPresenter(this)

        binding.btnSaveCreateAccount.setOnClickListener {

            presenter.createNewAccount(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString(),
                findNavController(),
                view
            )
        }
    }
}





