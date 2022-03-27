package com.example.timetablemanager.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.timetablemanager.MainActivity
import com.example.timetablemanager.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class register:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val registerEmail:EditText= findViewById(R.id.registerEmail)
        val registerPassword:EditText=findViewById(R.id.registerPassword)
        val registerButton:Button=findViewById(R.id.registerButton)
        val registertoLogin:TextView=findViewById(R.id.registerToLogin)

        registertoLogin.setOnClickListener {
            val intent=Intent(this@register, login::class.java)
            startActivity(intent)
        }


        registerButton.setOnClickListener {

            if (registerEmail.text.isEmpty()){
                Toast.makeText(this@register,
                    "Please enter the Email",
                    Toast.LENGTH_LONG).show()
            } else if (registerPassword.text.isEmpty()){
                Toast.makeText(this@register,
                    "Please enter the password",
                    Toast.LENGTH_LONG).show()
            } else {
                val email:String=registerEmail.text.toString()
                val password:String=registerPassword.text.toString()

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            Toast.makeText(this@register,
                                "You are registered successfully.",
                                Toast.LENGTH_LONG).show()

                            val intent= Intent(this@register, MainActivity::class.java)
                            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id",firebaseUser.uid)
                            intent.putExtra("email_id",email)
                            startActivity(intent)
                            finish()
                        } else{
                            Toast.makeText(this@register,
                                "task.exception!!.message.toString()",
                                Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }


    }
}