package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import bootcamp.cl.proyecto_agenda.DataBase.ConstantUtil.toError
import bootcamp.cl.proyecto_agenda.Presenters.CreateAccountPresenterImpl
import bootcamp.cl.proyecto_agenda.databinding.FragmentCreateAccountBinding


class CreateAccount : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using data binding
        binding = FragmentCreateAccountBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create an instance of CreateAccountPresenter
        val presenter = CreateAccountPresenterImpl(this)

        binding.userEmail.setOnFocusChangeListener { view, hasFocus ->
            // Perform email validation when the email field loses focus
            if (!hasFocus) {
                mailValidation()
            }
        }

        binding.btnSaveCreateAccount.setOnClickListener {
            // Check if any of the fields are empty
            if (binding.userName.text!!.isEmpty() ||
                binding.userEmail.text!!.isEmpty() ||
                binding.userPassword.text!!.isEmpty()
            ) {
                notEmptyFields()
                return@setOnClickListener
            } else {
                // Check if the password has at least 6 characters
                if (binding.userPassword.text.toString().length < 6) {
                    binding.userPassword.error = "Minimo 6 digitos"
                    return@setOnClickListener
                } else {
                    val mail = binding.userEmail.text.toString().trim()
                    // Check if the email is valid
                    if (!isValidEmail(mail)) {
                        binding.userEmail.error = "Mail invalido"
                        return@setOnClickListener
                    } else {
                        // Call the createNewAccount function in the presenter
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
        }
    }

    private fun mailValidation() {
        // Perform email validation and show error message if invalid
        val mail = binding.userEmail.text.toString().trim()
        if (!isValidEmail(mail)) {
            binding.mailErrorText.text = "Mail invalido"
            binding.mailErrorText.visibility = View.VISIBLE
        } else {
            binding.mailErrorText.visibility = View.GONE
        }
    }

    private fun notEmptyFields() {
        // Set error messages for empty fields
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

    private fun isValidEmail(email: String): Boolean {
        // Validate email format using a regular expression
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}




