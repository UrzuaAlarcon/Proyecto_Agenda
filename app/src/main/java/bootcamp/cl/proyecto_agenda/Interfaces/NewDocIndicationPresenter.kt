package bootcamp.cl.proyecto_agenda.Interfaces

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface NewDocIndicationPresenter {

    fun checkPermissions()

    fun requestCameraPermission()

    fun takePictureWithPhoneCamera()

    fun saveImageToInternalStorage(imageBitMap: Bitmap, context: Context): Uri?


}