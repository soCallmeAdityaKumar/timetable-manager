package com.example.timetablemanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class login:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val logInToRegister:TextView=findViewById(R.id.logintoRegister)

        logInToRegister?.setOnClickListener {
            val intent = Intent(this@login, register::class.java)
            startActivity(intent)
        }
        val loginEmail:EditText=findViewById(R.id.loginEmail)
        val loginPassword:EditText=findViewById(R.id.loginPassword)
        val loginButton:Button=findViewById(R.id.loginButton)
        val forgotPassword:TextView=findViewById(R.id.logintoForgotPassword)

        forgotPassword?.setOnClickListener {
            val intent=Intent(this@login,forgotpassword::class.java)
            startActivity(intent)
        }

            loginButton?.setOnClickListener {

                if (loginEmail.text.isEmpty()){
                    Toast.makeText(this@login,
                        "Please enter the Email",
                        Toast.LENGTH_LONG).show()
                } else if (loginPassword.text.isEmpty()){
                    Toast.makeText(this@login,
                        "Please enter the password",
                        Toast.LENGTH_LONG).show()
                } else {
                    val email:String=loginEmail.text.toString()
                    val password:String=loginPassword.text.toString()

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(this@login,
                                    "You are registered successfully.",
                                    Toast.LENGTH_LONG).show()

                                val intent= Intent(this@login,MainActivity::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",firebaseUser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            } else{
                                Toast.makeText(this@login,
                                    "task.exception!!.message.toString()",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }

    }

}