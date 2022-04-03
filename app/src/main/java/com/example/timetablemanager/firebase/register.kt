package com.example.timetablemanager.firebase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.timetablemanager.MainActivity
import com.example.timetablemanager.R
import com.example.timetablemanager.firestore.user
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class register:AppCompatActivity() {
 lateinit var database:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        database= FirebaseFirestore.getInstance()


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
                            addUser()
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
                                "please check value",
                                Toast.LENGTH_LONG).show()
                        }
                    }
            }


        }


    }
    private fun addUser(){
        var Email:EditText=findViewById(R.id.registerEmail)
        var name:EditText=findViewById(R.id.name)
        var registerButton:Button=findViewById(R.id.registerButton)

        if(Email.text.toString().isNotEmpty()&& name.text.toString().isNotEmpty()){
            var user=user(name.text.toString(),Email.text.toString(), profile = "abc")
            database.collection("Users").add(user)
                .addOnSuccessListener { object :OnSuccessListener<DocumentReference>{
                    override fun onSuccess(p0: DocumentReference?) {
                      Email.setText("")
                        name.setText("")

                    }
                } }.addOnFailureListener(object : OnFailureListener{
                    override fun onFailure(p0: Exception) {
                        Toast.makeText(applicationContext,"Failed to add",Toast.LENGTH_LONG).show()
                    }

                } )
        }
        else{
            registerButton.visibility= View.VISIBLE
        }
    }
}