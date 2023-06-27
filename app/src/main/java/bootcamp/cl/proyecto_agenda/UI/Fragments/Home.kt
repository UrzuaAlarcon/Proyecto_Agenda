package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.toError
import bootcamp.cl.proyecto_agenda.Presenters.HomePresenterIMPL
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentHomeBinding

class Home() : Fragment(R.layout.fragment_home) {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // If UID exists in preferences, navigate to the main_Fragment2

        val preferences = context?.getSharedPreferences(
            getString
                (R.string.preferences_file), Context.MODE_PRIVATE
        )
        val uid = preferences?.getString("UID", null)


        if (uid != null) {
            findNavController().navigate(R.id.action_home2_to_main_Fragment2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);

        val presenter = HomePresenterIMPL(this)

        //Navigate to the the create account view

        binding.btnCreateAccount.setOnClickListener {

            findNavController().navigate(R.id.action_home2_to_createAccount2)

        }

        //Login the user
        binding.btnLogin.setOnClickListener {

            if (binding.singInUserName.text!!.isEmpty() || binding.SingInUserPassword.text!!.isEmpty()) {

                notEmptyFields()
                return@setOnClickListener
            } else {
                presenter.singIn(
                    binding.singInUserName.text.toString(),
                    binding.SingInUserPassword.text.toString(),
                    findNavController(),
                    view
                )
            }
        }
    }


    private fun notEmptyFields() {
        // Set error messages for empty fields
        if (binding.singInUserName.text!!.isEmpty()) {
            binding.singInUserName.toError()
        }

        if (binding.SingInUserPassword.text!!.isEmpty()) {
            binding.SingInUserPassword.toError()
        }
    }

}