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

        val agendaDb: AgendaDb = AgendaDb.getDataBase(requireContext())
        val userDao = agendaDb.userDao()
        val handler = Handler(Looper.getMainLooper())

        val presenter = CreateAccountPresenter(this)

        binding.btnSaveCreateAccount.setOnClickListener {

            presenter.createNewAccount(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString(),
                findNavController(),
                view
            )

            handler.postDelayed({presenter.addNewUserToDataBase(userDao,
                binding.userEmail.text.toString(),
                binding.userName.text.toString(),
                getUid()
            )},2000)

        }
    }
}





