package com.ibtikar.apps.task.ui.detais

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.ibtikar.apps.task.R
import com.ibtikar.apps.task.ui.main.MainActivity
import com.ibtikar.apps.task.utils.ConstantsUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details_images.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class DetailsImagesActivity : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks {

    companion object {
        private const val WRITE_EXTERNAL = 122
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_images)

        getPermission()
        setView()

        actorFullImgID.setOnClickListener(View.OnClickListener {
            showAlertDialog()
        })

    }

    private fun setView() {
        val Img = intent.extras!!.getString("image")
        Picasso.get().load(ConstantsUtils.BASE_IMAGE_SOURCE + Img).into(actorFullImgID)
    }

    private fun showAlertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        val customLayout: View =
            LayoutInflater.from(this).inflate(R.layout.custom_alert_dialog, null)
        builder.setView(customLayout)

        val yes: Button = customLayout.findViewById(R.id.yesBtn)
        yes.setOnClickListener(View.OnClickListener {
            saveToGallery()
        })

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun saveToGallery() {

        val bitmap = actorFullImgID.drawable.toBitmap()

        var outStream: FileOutputStream? = null
        val sdCard: File = Environment.getExternalStorageDirectory()

        val dir = File(sdCard.absolutePath + "/Demo")
        dir.mkdirs()
        val fileName =
            String.format("%d.jpg", System.currentTimeMillis())
        val outFile = File(dir, fileName)

        try {
            outStream = FileOutputStream(outFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
            Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        try {
            outStream!!.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            outStream!!.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        intent.data = Uri.fromFile(outFile)
        sendBroadcast(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(WRITE_EXTERNAL)
    private fun getPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // do nothing
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.rationale_sms),
                WRITE_EXTERNAL, Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(
            this, "You must accept permission access if you want to download image !!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(
            this, "Now you can download any image you needed",
            Toast.LENGTH_SHORT
        ).show();
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}