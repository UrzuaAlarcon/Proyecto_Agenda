package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import bootcamp.cl.proyecto_agenda.Presenters.NewDocIndicationPresenterImpl
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewDocIndicationBinding


class NewDocIndicationFragment : Fragment() {
    val presenter = NewDocIndicationPresenterImpl(this)
    lateinit var binding: FragmentNewDocIndicationBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNewDocIndicationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCamera.setOnClickListener {

            presenter.checkPermissions()

        }

    }

/*    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 777) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.takePictureWithPhoneCamera()
            } else {

                Toast.makeText(context,
                    "permisos rechazados por primera vez",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode ==
            777 && data != null
        ) {

            val imageBitMap = data.extras?.get("data") as Bitmap
            if (imageBitMap != null) {

                binding.IndicationImage.setImageBitmap(imageBitMap)

            } else {

                Toast.makeText(context, "No se pudo tomar la foto", Toast.LENGTH_SHORT).show()

            }

        }


    }
}