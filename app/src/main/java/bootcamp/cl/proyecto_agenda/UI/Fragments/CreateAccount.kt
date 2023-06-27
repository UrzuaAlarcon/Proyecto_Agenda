package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.DataBase.AgendaDb
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.getUid
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.toError
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

            if (binding.userName.text!!.isEmpty() ||
                binding.userEmail.text!!.isEmpty() ||
                binding.userPassword.text!!.isEmpty()
            ) {

                notEmptyFields()

                return@setOnClickListener

            } else {
                presenter.createNewAccount(
                    binding.userName.text.toString(),
                    binding.userEmail.text.toString(),
                    binding.userPassword.text.toString(),
                    findNavController(),
                    view
                )
            }
        }
    }

    private fun notEmptyFields() {

        if (binding.userName.text!!.isEmpty()) {
            binding.userName.toError()
        }

        if (binding.userEmail.text!!.isEmpty()) {
            binding.userEmail.toError()
        }

        if (binding.userPassword.text!!.isEmpty()) {
            binding.userPassword.toError()
        }

    }
}





