package com.example.timetablemanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class forgotpassword:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpassword)

        val submit:Button=findViewById(R.id.forgotpasswordSubmitButton)
        val emailId:EditText=findViewById(R.id.forgotpasswordEmail)

        submit?.setOnClickListener {
            val email=emailId.text.toString()

            if (email.isEmpty()){
                Toast.makeText(this,
                    "Please enter your EmailId",Toast.LENGTH_LONG).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{ task->

                        if(task.isSuccessful){
                            Toast.makeText(this,"Email sent successfully to reset your passoword",
                            Toast.LENGTH_LONG).show()
                            finish()
                        }
                        else{
                            Toast.makeText(this,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_LONG).show()
                        }
                    }
            }

        }

    }
}