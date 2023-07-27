package bootcamp.cl.proyecto_agenda.Presenters

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import bootcamp.cl.proyecto_agenda.Interfaces.NewDocIndicationPresenter
import bootcamp.cl.proyecto_agenda.UI.Fragments.NewDocIndicationFragment

class NewDocIndicationPresenterImpl(private val newIndication: NewDocIndicationFragment) :
    NewDocIndicationPresenter, Fragment() {
    override fun checkPermissions() {
        //check camera permissions
        if (ContextCompat.checkSelfPermission(newIndication.requireContext(), CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            //if permission is not granted, ask for it
            requestCameraPermission()
        } else {

            takePictureWithPhoneCamera()
        }
    }

    override fun requestCameraPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                newIndication.requireActivity(),
                CAMERA
            )
        ) {
            Toast.makeText(newIndication.context, "permisos rechazados", Toast.LENGTH_SHORT).show()
        } else {
            //ask for permission
            ActivityCompat.requestPermissions(newIndication.requireActivity(), arrayOf(CAMERA), 777)
        }


    }

    override fun takePictureWithPhoneCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        newIndication.startActivityForResult(intent, 777)
    }
}

