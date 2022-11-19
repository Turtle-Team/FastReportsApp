package com.iubip.fastreportsapp.utils

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.FileOutputStream
import java.io.InputStream


class Download {
    @SuppressLint("SdCardPath")
    fun saveFile(body: String, path: String, fileName: String): String {
        Log.e("SYSTEM", Environment.getExternalStorageDirectory().path.toString())

        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteInputStream()
            val fos = FileOutputStream("/storage/self/primary/Download/fasts/$fileName")
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                val read: Int = 0
                while (input.read(buffer).also { read == it } != 1){
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return path
        }catch (e: Exception){
            Log.e("saveFile", e.toString())
        } finally {
            input?.close()
        }
        return ""
    }
}
