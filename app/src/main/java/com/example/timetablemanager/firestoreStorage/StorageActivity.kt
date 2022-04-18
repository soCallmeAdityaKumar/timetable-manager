package com.example.timetablemanager.firestoreStorage

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.timetablemanager.databinding.StorageprofileBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter


class StorageActivity:AppCompatActivity() {

    lateinit var binding: StorageprofileBinding
    lateinit var ImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= StorageprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SelectPhotobutton.setOnClickListener {
            selectImage()
        }
        binding.UploadImageView.setOnClickListener {
            uploadImage()
        }
    }

    private fun uploadImage() {

        val progress=ProgressDialog(this)
        progress.setMessage("Uploading File....")
        progress.setCancelable(false)
        progress.show()


        val formatter=SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now=Date()
        val fileName=formatter.format(now)
        val storageReference=FirebaseStorage.getInstance().getReference("image/$fileName")

        storageReference.putFile(ImageUri).
        addOnSuccessListener {
            binding.UploadImageView.setImageURI(null)
            Toast.makeText(this,"Successfully uploaded the image",Toast.LENGTH_LONG).show()
            if(progress.isShowing)
                progress.dismiss()
        }
            .addOnFailureListener{
                if(progress.isShowing)progress.dismiss()
                Toast.makeText(this,"Failed",Toast.LENGTH_LONG).show()
            }

    }

    private fun selectImage() {
        val intent=Intent()

        intent.type="images/"
        intent.action=Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100 && resultCode== RESULT_OK){
            ImageUri=data?.data!!
            binding.UploadImageView.setImageURI(ImageUri)

        }
    }

}