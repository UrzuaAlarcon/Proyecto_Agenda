package bootcamp.cl.proyecto_agenda.UI.Fragments

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.text.intl.Locale
import bootcamp.cl.proyecto_agenda.Presenters.NewDocIndicationPresenterImpl
import bootcamp.cl.proyecto_agenda.R
import bootcamp.cl.proyecto_agenda.databinding.FragmentNewDocIndicationBinding
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


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


                val imageUri = presenter.saveImageToInternalStorage(imageBitMap, requireContext())

                if (imageUri != null) {

                    Handler(Looper.getMainLooper()).postDelayed({

                        binding.IndicationImage.setImageURI(imageUri)
                    }, 500)

                }

            }

        } else {

            Toast.makeText(context, "No se pudo tomar la foto", Toast.LENGTH_SHORT).show()

        }

    }

}


