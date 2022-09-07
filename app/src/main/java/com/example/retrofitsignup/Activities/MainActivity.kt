package com.example.retrofitsignup.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitsignup.Api.RetrofitClient
import com.example.retrofitsignup.R
import com.example.retrofitsignup.models.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSignUp = findViewById<Button>(R.id.buttonSignUp)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        buttonSignUp.setOnClickListener{
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if(email.isEmpty()){
                etEmail.error = "Email Required"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                etPassword.error = "Password Required"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.createUser(email , password)
                .enqueue(object:Callback<DefaultResponse>{
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }

                })


        }
    }
}