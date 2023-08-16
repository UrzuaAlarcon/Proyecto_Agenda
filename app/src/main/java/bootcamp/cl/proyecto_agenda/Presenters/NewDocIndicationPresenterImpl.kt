package bootcamp.cl.proyecto_agenda.Presenters

import android.Manifest.permission.CAMERA
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import bootcamp.cl.proyecto_agenda.Interfaces.NewDocIndicationPresenter
import bootcamp.cl.proyecto_agenda.UI.Fragments.NewDocIndicationFragment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URI
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

    override fun saveImageToInternalStorage(imageBitMap: Bitmap, context: Context): Uri? {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_${timeStamp}.jpg"
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, imageFileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        try {
            val fileOutputStream: FileOutputStream
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                // En Android 10 y versiones posteriores, utiliza el almacenamiento interno privado de la aplicación

                val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fileOutputStream = resolver.openOutputStream(imageUri!!) as FileOutputStream

            } else {
                // Antes de Android 10, se puede utilizar la ruta de almacenamiento externo tradicional
                val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val imageFile = File(directory, imageFileName)
                fileOutputStream = FileOutputStream(imageFile)
            }

            imageBitMap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()

            Toast.makeText(context, "Imagen guardada correctamente", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(context, "Error al guardar la imagen", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }

        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        return imageUri

    }
}

